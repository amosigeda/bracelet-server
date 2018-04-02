package com.bracelet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bracelet.dto.*;
import com.bracelet.entity.BloodOxygen;
import com.bracelet.entity.BloodSugar;
import com.bracelet.entity.HeartPressure;
import com.bracelet.entity.HeartRate;
import com.bracelet.entity.Location;
import com.bracelet.entity.Step;
import com.bracelet.service.IBloodOxygenService;
import com.bracelet.service.IBloodSugarService;
import com.bracelet.service.IHeartPressureService;
import com.bracelet.service.IHeartRateService;
import com.bracelet.service.ILocationService;
import com.bracelet.service.IStepService;
import com.bracelet.util.Utils;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/status")
public class CommonController extends BaseController {
	@Autowired
	IHeartPressureService heartPressureService;
	@Autowired
	ILocationService locationService;
	@Autowired
	IHeartRateService heartRateService;
	@Autowired
	IStepService stepService;
	@Autowired
	IBloodSugarService bloodSugarService;
	@Autowired
	IBloodOxygenService bloodOxygenService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@ResponseBody
	@RequestMapping("/search/latest/{token}")
	public HttpBaseDto latest(@PathVariable String token) {
		Long user_id = checkTokenAndUser(token);
		LatestStatusInfo latestStatusInfo = new LatestStatusInfo();
		HeartPressure heartPressure = heartPressureService.getLatest(user_id);
		Map<String, Object> heartPressureDataMap = new HashMap<>();
		if (heartPressure != null) {
			heartPressureDataMap.put("maxHeartPressure", heartPressure.getMax_heart_pressure());
			heartPressureDataMap.put("minHeartPressure", heartPressure.getMin_heart_pressure());
			heartPressureDataMap.put("status", Utils.checkHeartPressure(heartPressure.getMax_heart_pressure(),
					heartPressure.getMin_heart_pressure()));
			heartPressureDataMap.put("timestamp", heartPressure.getUpload_time().getTime());
		}
		latestStatusInfo.setHeartPressure(heartPressureDataMap);

		HeartRate heartRate = heartRateService.getLatest(user_id);
		Map<String, Object> heartRateDataMap = new HashMap<>();
		if (heartRate != null) {
			heartRateDataMap.put("heartRate", heartRate.getHeart_rate());
			heartRateDataMap.put("status", Utils.checkHeartRate(heartRate.getHeart_rate()));
			heartRateDataMap.put("timestamp", heartRate.getUpload_time().getTime());
		}
		latestStatusInfo.setHeartRate(heartRateDataMap);

		Location location = locationService.getLatest(user_id);
		Map<String, Object> locationDataMap = new HashMap<>();
		if (location != null) {
			locationDataMap.put("lat", location.getLat());
			locationDataMap.put("lng", location.getLng());
			locationDataMap.put("timestamp", location.getUpload_time().getTime());
		}
		latestStatusInfo.setLocation(locationDataMap);

		BloodSugar bloodSugar = bloodSugarService.getLatest(user_id);
		Map<String, Object> bloodSugarDataMap = new HashMap<>();
		if (bloodSugar != null) {
			bloodSugarDataMap.put("bloodSugar", bloodSugar.getBlood_sugar());
			bloodSugarDataMap.put("timestamp", bloodSugar.getUpload_time().getTime());
		}
		latestStatusInfo.setBloodSugar(bloodSugarDataMap);

		BloodOxygen bloodOxygen = bloodOxygenService.getLatest(user_id);
		Map<String, Object> bloodOxygenDataMap = new HashMap<>();
		if (bloodOxygen != null) {
			bloodOxygenDataMap.put("bloodOxygen", bloodOxygen.getBlood_oxygen());
			bloodOxygenDataMap.put("pluseRate", bloodOxygen.getPulse_rate());
			bloodOxygenDataMap.put("timestamp", bloodOxygen.getUpload_time().getTime());
		}
		latestStatusInfo.setBloodOxygen(bloodOxygenDataMap);

		HttpBaseDto dto = new HttpBaseDto();
		dto.setData(latestStatusInfo);
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public HttpBaseDto upload(String token, Integer maxHeartPressure, Integer minHeartPressure, Integer heartRate) {
		Long user_id = checkTokenAndUser(token);
		if (maxHeartPressure != null && minHeartPressure != null) {
			try {
				heartPressureService.insert(user_id, maxHeartPressure, minHeartPressure, Utils.getCurrentTimestamp());
			} catch (Exception e) {
				logger.info("heartPressureService.insert error.", e);
			}
		}

		if (heartRate != null) {
			heartRateService.insert(user_id, heartRate, Utils.getCurrentTimestamp());
		}

		HttpBaseDto dto = new HttpBaseDto();
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "/data/share/getlink", method = RequestMethod.POST)
	public HttpBaseDto getDataShareLink(String token) throws Exception {
		Long user_id = checkTokenAndUser(token);
		// 快照数据
		Map<String, Object> dataMap = new HashMap<>();
		// 血压
		HeartPressure heartPressure = heartPressureService.getLatest(user_id);
		if (heartPressure != null) {
			Map<String, Object> heartPressureDataMap = new HashMap<>();
			heartPressureDataMap.put("maxHeartPressure", heartPressure.getMax_heart_pressure());
			heartPressureDataMap.put("minHeartPressure", heartPressure.getMin_heart_pressure());
			List<Map<String, Object>> hpStatus = Utils.checkHeartPressure(heartPressure.getMax_heart_pressure(),
					heartPressure.getMin_heart_pressure());
			String maxStatus = "正常";
			String minStatus = "正常";
			for (Map<String, Object> statusMap : hpStatus) {
				int type = Integer.valueOf(String.valueOf(statusMap.get("type")));
				if (type == 1) {
					maxStatus = String.valueOf(statusMap.get("msg"));
				} else if (type == 2) {
					minStatus = String.valueOf(statusMap.get("msg"));
				}
			}
			heartPressureDataMap.put("maxStatus", maxStatus);
			heartPressureDataMap.put("minStatus", minStatus);
			heartPressureDataMap.put("timestamp", Utils.format14DateString(heartPressure.getUpload_time().getTime()));
			dataMap.put("heartPressure", heartPressureDataMap);
		}

		// 脉搏
		HeartRate heartRate = heartRateService.getLatest(user_id);
		if (heartRate != null) {
			Map<String, Object> heartRateDataMap = new HashMap<>();
			Map<String, Object> statusMap = Utils.checkHeartRate(heartRate.getHeart_rate());
			heartRateDataMap.put("heartRate", heartRate.getHeart_rate());
			heartRateDataMap.put("status", String.valueOf(statusMap.get("msg")));
			heartRateDataMap.put("timestamp", Utils.format14DateString(heartRate.getUpload_time().getTime()));
			dataMap.put("heartRate", heartRateDataMap);
		}

		// 步数
		Step step = stepService.getLatest(user_id);
		if (step != null) {
			Map<String, Object> stepDataMap = new HashMap<>();
			stepDataMap.put("step", step.getStep_number());
			dataMap.put("step", stepDataMap);
		}
		
		//血糖
		BloodSugar bloodSugar = bloodSugarService.getLatest(user_id);
		if (bloodSugar != null) {
			Map<String, Object> bloodSugarDataMap = new HashMap<>();
			bloodSugarDataMap.put("bloodSugar", bloodSugar.getBlood_sugar());
			bloodSugarDataMap.put("timestamp", Utils.format14DateString(bloodSugar.getUpload_time().getTime()));
			dataMap.put("bloodSugar", bloodSugarDataMap);
		}
		//血氧
		BloodOxygen bloodOxygen = bloodOxygenService.getLatest(user_id);
		if (bloodOxygen != null) {
			Map<String, Object> bloodOxygenDataMap = new HashMap<>();
			bloodOxygenDataMap.put("bloodOxygen", bloodOxygen.getBlood_oxygen());
			bloodOxygenDataMap.put("pluseRate", bloodOxygen.getPulse_rate());
			bloodOxygenDataMap.put("timestamp", Utils.format14DateString(bloodOxygen.getUpload_time().getTime()));
			dataMap.put("bloodOxygen", bloodOxygenDataMap);
		}
		
		dataMap.put("timestamp", Utils.format14DateString(new Date()));
		String link = JSON.toJSONString(dataMap);
		String sign = Utils.getMD5(Utils.MD5_MAGIC + link);
		String formatedLink = Base64.encodeBase64URLSafeString(link.getBytes("UTF-8"));
		String viewUrl = "/status/data/share/view/" + formatedLink + "?sign=" + sign;
		Map<String, Object> respMap = new HashMap<>();
		respMap.put("viewUrl", viewUrl);
		HttpBaseDto dto = new HttpBaseDto();
		dto.setData(respMap);
		return dto;
	}

	@RequestMapping("/data/share/view/{link}")
	public String dataShare(@PathVariable String link, @RequestParam String sign, ModelMap model) {
		// ½âÃÜlink
		String oriLink = new String(Base64.decodeBase64(link));
		String sign2 = Utils.getMD5(Utils.MD5_MAGIC + oriLink);
		if (sign.equals(sign2)) {
			JSONObject jsonObject = (JSONObject) JSON.parse(oriLink);
			// 血压
			JSONObject heartPressureJsonObj = jsonObject.getJSONObject("heartPressure");
			Map<String, Object> heartPressureDataMap = new HashMap<>();
			if (heartPressureJsonObj != null) {
				heartPressureDataMap.put("maxHeartPressure", heartPressureJsonObj.getString("maxHeartPressure"));
				heartPressureDataMap.put("minHeartPressure", heartPressureJsonObj.getString("minHeartPressure"));
				heartPressureDataMap.put("maxStatus", heartPressureJsonObj.getString("maxStatus"));
				heartPressureDataMap.put("minStatus", heartPressureJsonObj.getString("minStatus"));
				heartPressureDataMap.put("timestamp", heartPressureJsonObj.getString("timestamp"));
			}

			// 脉搏
			JSONObject heartRateJsonObj = jsonObject.getJSONObject("heartRate");
			Map<String, Object> heartRateDataMap = new HashMap<>();
			if (heartRateJsonObj != null) {
				heartRateDataMap.put("heartRate", heartRateJsonObj.getString("heartRate"));
				heartRateDataMap.put("status", heartRateJsonObj.getString("status"));
				heartRateDataMap.put("timestamp", heartRateJsonObj.getString("timestamp"));
			}

			// 步数
			JSONObject stepJsonObj = jsonObject.getJSONObject("step");
			Map<String, Object> stepDataMap = new HashMap<>();
			if (stepJsonObj != null) {
				stepDataMap.put("step", stepJsonObj.getString("step"));
			}
			
			// 血糖
			JSONObject bloodSugarJsonObj = jsonObject.getJSONObject("bloodSugar");
			Map<String, Object> bloodSugarDataMap = new HashMap<>();
			if (bloodSugarJsonObj != null) {
				bloodSugarDataMap.put("bloodSugar", bloodSugarJsonObj.getString("bloodSugar"));
				bloodSugarDataMap.put("timestamp",bloodSugarJsonObj.getString("timestamp"));
			}
			
			//血氧
			JSONObject bloodOxygenJsonObj = jsonObject.getJSONObject("bloodOxygen");
			Map<String, Object> bloodOxygenDataMap = new HashMap<>();
			if (bloodOxygenJsonObj != null) {
			    bloodOxygenDataMap.put("bloodOxygen", bloodOxygenJsonObj.getString("bloodOxygen"));
				bloodOxygenDataMap.put("pluseRate", bloodOxygenJsonObj.getString("pluseRate"));
				bloodOxygenDataMap.put("timestamp",bloodOxygenJsonObj.getString("timestamp"));
			}

			// 时间
			String timestamp = jsonObject.getString("timestamp");

			model.put("heartPressureDataMap", heartPressureDataMap);
			model.put("heartRateDataMap", heartRateDataMap);
			model.put("stepDataMap", stepDataMap);
			model.put("bloodSugarDataMap", bloodSugarDataMap);
			model.put("bloodOxygenDataMap", bloodOxygenDataMap);
			model.put("timestamp", timestamp);
			return "dataShare";
		}
		return "warn";
	}

	// http://121.196.232.11:8084/bracelet-server/status/data/share/view/eyJzdGVwIjp7InN0ZXAiOjIxNn0sInRpbWVzdGFtcCI6IjIwMTctMTItMjAgMTI6MDQ6NTcifQ?sign=3E18FF528DF15BE2D556D014E2089C00

}
