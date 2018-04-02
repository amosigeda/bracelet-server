package com.bracelet.socket.business.impl;

import com.bracelet.service.IFenceService;
import io.netty.channel.Channel;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bracelet.dto.SocketBaseDto;
import com.bracelet.dto.SocketLoginDto;
import com.bracelet.dto.SosDto;
import com.bracelet.entity.Location;
import com.bracelet.service.ILocationService;
import com.bracelet.service.IPushlogService;
import com.bracelet.service.ISensitivePointService;
import com.bracelet.service.ITokenInfoService;
import com.bracelet.util.GpsCorrect;
import com.bracelet.util.HttpClientGet;
import com.bracelet.util.PushUtil;
import com.bracelet.util.Utils;

@Service("locationPointService")
public class LocationPointService extends AbstractBizService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	ILocationService locationService;
	@Autowired
	IFenceService fenceService;
	@Autowired
	ISensitivePointService sensitivePointService;
	@Autowired
	ITokenInfoService tokenInfoService;
	@Autowired
	IPushlogService pushlogService;

	/**
	 * 高德地图key，基站查询
	 */

	public SocketBaseDto process1(SocketLoginDto socketLoginDto, JSONObject jsonObject, Channel channel) {
		Long userId = socketLoginDto.getUser_id();
		String imei = socketLoginDto.getImei();
		SosDto sosDto = new SosDto();
		/*String target = tokenInfoService.getTokenByUserId(userId);
		String title = "设备定位";
		String notifyContent = "收到设备定位，请点击查看";
*/
		// 0gps 1基站
		String locationType = jsonObject.getString("locationType");
		String lat = null; // 纬度
		String lng = null; // 经度
		int accuracy = 0;
		Long user_id = socketLoginDto.getUser_id();
		logger.info("[LocationService]处理位置信息,user_id:" + user_id + ",imei:" + socketLoginDto.getImei() + ",no:"
				+ jsonObject.getString("no"));
		Integer statusType = Integer.valueOf(0);
		statusType = jsonObject.getInteger("a");

		if ("0".equals(locationType)) {
			// GPS
			logger.info("[LocationService]处理GPS位置信息：" + jsonObject.toJSONString());
			JSONArray jsonArray = jsonObject.getJSONArray("data");
			for (int i = 0; jsonArray != null && i < jsonArray.size(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.get(i);
				JSONObject jsonArray2 = jsonObject2.getJSONObject("gps");
				lng = jsonArray2.getString("lng");
				lat = jsonArray2.getString("lat");
				accuracy = jsonArray2.getIntValue("accuracy");
				long timestamp = jsonObject2.getLongValue("timestamp");
				try {
					if (!"0.000000".equals(lat) && !"0.000000".equals(lng)) {
						String url = Utils.GPS_URL + "?key=" + Utils.GPS_KEY + "&coordsys=gps&locations=" + lng + ","
								+ lat;
						logger.info("[LocationService]请求高德GPS位置转换,URL:" + url);
						String responseJsonString = HttpClientGet.get(url);
						logger.info("[LocationService]请求高德坐标转换，应答数据:" + responseJsonString);

						JSONObject responseJsonObject = (JSONObject) JSON.parse(responseJsonString);
						String status = responseJsonObject.getString("status");
						String locations = responseJsonObject.getString("locations");
						if ("1".equals(status) && locations != null) {
							locations = locations.split(";")[0];
							String[] locationsArr = locations.split(",");
							if (locationsArr.length == 2) {

								double latlng[] = new double[2];
								latlng[0] = new Double(locationsArr[1]);
								latlng[1] = new Double(locationsArr[0]);
								GpsCorrect.transform(Double.parseDouble(lat), Double.parseDouble(lng), latlng);

								locationsArr[1] = latlng[0] + "";
								locationsArr[0] = latlng[1] + "";

								logger.info("GPS转换后再纠偏后的的经纬度=" + locationsArr[1] + "," + locationsArr[0]);
								locationService.insert(user_id, imei, 0, locationType, locationsArr[1], locationsArr[0],
										accuracy, statusType, new Timestamp(timestamp * 1000), 3,"");

								sosDto.setLat(locationsArr[1]);
								sosDto.setLng(locationsArr[0]);
								sosDto.setLocationType(0);
								sosDto.setTimestamp(System.currentTimeMillis());
								String content = JSON.toJSONString(sosDto);
								/*PushUtil.push(target, title, content, notifyContent);

								this.pushlogService.insert(userId, socketLoginDto.getImei(), 0, target, title, content);

								// 电子围栏检查
								fenceService.checkFenceArea(user_id, socketLoginDto.getImei(), locationsArr[1],
										locationsArr[0], timestamp * 1000);
								// 敏感区域检查
								sensitivePointService.checkSensitivePointArea(user_id, socketLoginDto.getImei(),
										locationsArr[1], locationsArr[0], timestamp * 1000);*/
							} else {
								logger.warn("[LocationService]请求高德转换坐标信息发生错误:" + jsonObject2.toJSONString());
							}
						} else {
							logger.warn("[LocationService]请求高德转换坐标信息发生错误:" + jsonObject2.toJSONString());
						}
					} else {
						logger.warn("[LocationService]收到0.0错误位置信息，请通知设备端修正:" + jsonObject2.toJSONString());
					}
				} catch (Exception e) {
					logger.warn("[LocationService]保存位置信息发生错误:" + jsonObject2.toJSONString(), e);
				}
			}
		} else {
			logger.warn("[LocationService]未知的locationType请求，请通知设备端修正:" + locationType);
		}

		SocketBaseDto dto = new SocketBaseDto();
		dto.setType(jsonObject.getIntValue("type"));
		dto.setNo(jsonObject.getString("no"));
		dto.setTimestamp(new Date().getTime());
		dto.setStatus(0);
		return dto;
	}
}
