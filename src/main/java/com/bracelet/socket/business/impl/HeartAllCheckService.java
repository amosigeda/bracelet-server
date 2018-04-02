package com.bracelet.socket.business.impl;

import io.netty.channel.Channel;

import java.awt.geom.Point2D;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bracelet.dto.SocketBaseDto;
import com.bracelet.dto.SocketLoginDto;
import com.bracelet.dto.SosDto;
import com.bracelet.dto.VoltageDto;
import com.bracelet.entity.FrequencyContro;
import com.bracelet.entity.Location;
import com.bracelet.entity.OddShape;
import com.bracelet.service.IFenceService;
import com.bracelet.service.IHeartRateService;
import com.bracelet.service.ILocationService;
import com.bracelet.service.IPushlogService;
import com.bracelet.service.ISensitivePointService;
import com.bracelet.service.IStepService;
import com.bracelet.service.ITokenInfoService;
import com.bracelet.service.IVoltageService;
import com.bracelet.util.HttpClientGet;
import com.bracelet.util.PushUtil;
import com.bracelet.util.Utils;

/**
 * 系统心跳
 * 
 */
// public class HeartCheck implements IService {
@Component("heartAllCheck")
public class HeartAllCheckService extends AbstractBizService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	IHeartRateService heartRateService;
	@Autowired
	IStepService stepService;
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
	@Autowired
	IVoltageService voltageService;

	@Override
	protected SocketBaseDto process1(SocketLoginDto socketLoginDto, JSONObject jsonObject, Channel channel) {
		logger.info("===设备心跳总包：" + jsonObject.toJSONString());

		String titlee = "设备定位";
		Long userId = socketLoginDto.getUser_id();
		String imei = socketLoginDto.getImei();
		SosDto sosDto = new SosDto();
		String target = tokenInfoService.getTokenByUserId(userId);

		String lat = null; // 纬度
		String lng = null; // 经度
		String address=null;

		Integer statusType = Integer.valueOf(0);
		statusType = jsonObject.getInteger("a");

		String locationType = "1";
		long timeStamp = jsonObject.getLongValue("timestamp");
		JSONArray jsonArray = jsonObject.getJSONArray("data");

		for (int i = 0; jsonArray != null && i < jsonArray.size(); i++) {
			JSONObject jsonObject2 = (JSONObject) jsonArray.get(i);

			Integer stepNumber = jsonObject2.getInteger("stepNumber");
			this.stepService.insert(userId, imei, stepNumber, new Timestamp(timeStamp * 1000));
			// 步数增加
			Integer voltage = jsonObject2.getInteger("voltage");
			VoltageDto voltageDto = new VoltageDto();
			voltageService.insert(userId, imei, voltage, new Timestamp(timeStamp * 1000));
			// 电量增加
			if (voltage <= 20) {
				String title = "设备低电量提醒";
				String notifyContent = "设备低电量提醒，请点击查看";
				voltageDto.setVoltage(voltage);
				voltageDto.setTimestamp(System.currentTimeMillis());
				String content = JSON.toJSONString(voltageDto);
				PushUtil.push(target, title, content, notifyContent);
				this.pushlogService.insert(userId, socketLoginDto.getImei(), 0, target, title, content);
				// 小于百分之20推送
			}

			JSONObject lbsJsonObject = jsonObject2.getJSONObject("lbs");
			long timestamp = jsonObject.getLongValue("timestamp");
			if (lbsJsonObject != null) {
				String accesstype = lbsJsonObject.getString("accesstype");
				accesstype = accesstype == null ? lbsJsonObject.getString("accesstypet") : accesstype;
				String imsi = lbsJsonObject.getString("imsi");// 手机 imei 号
				String smac = lbsJsonObject.getString("smac");
				String serverip = lbsJsonObject.getString("serverip");
				String cdma = lbsJsonObject.getString("cdma");
				String network = lbsJsonObject.getString("network");
				String tel = lbsJsonObject.getString("tel");
				String bts = lbsJsonObject.getString("bts");
				String nearbts = lbsJsonObject.getString("nearbts");
				String mmac = lbsJsonObject.getString("mmac");
				String macs = lbsJsonObject.getString("macs");
				if (imsi == null) {
					imsi = socketLoginDto != null ? socketLoginDto.getImei() : null;
				}

				StringBuilder myurlBuilder = new StringBuilder(Utils.URL);
				myurlBuilder.append("?key=").append(Utils.KEY).append("&output=json");
				if (accesstype != null && accesstype.length() != 0) {
					myurlBuilder.append("&accesstype=").append(accesstype);
				}
				if (imsi != null && imsi.length() != 0) {
					myurlBuilder.append("&imsi=").append(imsi);
				}
				if (smac != null && smac.length() != 0) {
					myurlBuilder.append("&smac=").append(smac);
				}
				if (serverip != null && serverip.length() != 0) {
					myurlBuilder.append("&serverip=").append(serverip);
				}
				if (cdma != null && cdma.length() != 0) {
					myurlBuilder.append("&cdma=").append(cdma);
				}
				if (network != null && network.length() != 0) {
					myurlBuilder.append("&network=").append(network);
				}
				if (tel != null && tel.length() != 0) {
					myurlBuilder.append("&tel=").append(tel);
				}
				if (bts != null && bts.length() != 0) {
					myurlBuilder.append("&bts=").append(bts);
				}
				if (nearbts != null && nearbts.length() != 0) {
					myurlBuilder.append("&nearbts=").append(nearbts);
				}
				if (mmac != null && mmac.length() != 0) {
					myurlBuilder.append("&mmac=").append(mmac);
				}
				if (macs != null && mmac.length() != 0) {
					myurlBuilder.append("&macs=").append(macs);
				}

				try {
					logger.info("[LocationService]根据基站查询位置,URL:" + myurlBuilder.toString());
					String responseJsonString = HttpClientGet.urlReturnParams(myurlBuilder.toString());
					logger.info("[LocationService]高德返回数据:" + responseJsonString);
					if (responseJsonString != null) {
						JSONObject responseJsonObject = (JSONObject) JSON.parse(responseJsonString);
						String status = responseJsonObject.getString("status");
						String info = responseJsonObject.getString("info");

						if ("1".equals(status)) {
							JSONObject resultJsonObject = responseJsonObject.getJSONObject("result");
							if (resultJsonObject == null) {
								logger.warn("[LocationService] 返回的result为空");
								continue;
							}
							String location = resultJsonObject.getString("location");
						      address=responseJsonObject.getString("desc");
							if (location == null) {
								logger.warn("[LocationService] 返回的location为空");
								continue;
							}

							String[] arr = location.split(",");
							if (arr.length == 2) {
								lat = arr[1];
								lng = arr[0];

							} else {
								logger.warn("[LocationService] 返回的location数据错误:" + location);
							}

							Location locationList = locationService.getLatest(userId);
							Location showlocationList = locationService.getShowLatest(userId);
							if (locationList != null) {
								long lastTime = locationList.getUpload_time().getTime();// 上次时间
								long nowTime = System.currentTimeMillis() / 1000;
								Long s = (nowTime - lastTime / 1000) / 60;
								logger.info("现在的时间为=" + nowTime + ",上一次时间为=" + lastTime + ",计算出的时间差为=" + s);
								if (s >= 5) {
									//大于五分钟必推送
									sosDto.setLat(lat);
									sosDto.setLng(lng);
									sosDto.setLocationType(1);
									sosDto.setTimestamp(System.currentTimeMillis());
									String content = JSON.toJSONString(sosDto);
									PushUtil.pushMessage(target, titlee, content, 0);
									
                                //接着判断显示点是否大于五分钟
									if(showlocationList!=null){
										lastTime = showlocationList.getUpload_time().getTime();// 上次点的显示时间
										 s = (nowTime - lastTime / 1000) / 60;
										 if(s>=5){
											 locationService.insert(userId, imei, 1, locationType, lat, lng, Integer.valueOf(0),
														statusType, new Timestamp(timestamp * 1000), 1,address);
										 }else{
											 double distance = Utils.calcDistance(Double.parseDouble(lng),
														Double.parseDouble(lat), Double.parseDouble(showlocationList.getLng()),
														Double.parseDouble(showlocationList.getLat()));
												if (distance > Utils.distance) {
													 locationService.insert(userId, imei, 1, locationType, lat, lng, Integer.valueOf(0),
																statusType, new Timestamp(timestamp * 1000), 1,address);
												} else{
													 locationService.insert(userId, imei, 1, locationType, lat, lng, Integer.valueOf(0),
																statusType, new Timestamp(timestamp * 1000), 0,address);
												}
										 }
									}else{
										locationService.insert(userId, imei, 1, locationType, lat, lng, Integer.valueOf(0),
												statusType, new Timestamp(timestamp * 1000), 1,address);
									}


								} else {
									//小于五分钟的
									if(showlocationList!=null){
										lastTime = showlocationList.getUpload_time().getTime();// 上次点的显示时间
										 s = (nowTime - lastTime / 1000) / 60;
										 if(s>=5){
											 locationService.insert(userId, imei, 0, locationType, lat, lng, Integer.valueOf(0),
														statusType, new Timestamp(timestamp * 1000), 1,address);
										 }else{
											 double distance = Utils.calcDistance(Double.parseDouble(lng),
														Double.parseDouble(lat), Double.parseDouble(showlocationList.getLng()),
														Double.parseDouble(showlocationList.getLat()));
												if (distance > Utils.distance) {
													 locationService.insert(userId, imei, 0, locationType, lat, lng, Integer.valueOf(0),
																statusType, new Timestamp(timestamp * 1000), 1,address);
												} else{
													 locationService.insert(userId, imei, 0, locationType, lat, lng, Integer.valueOf(0),
																statusType, new Timestamp(timestamp * 1000), 0,address);
												}
										 }
									}else{
										locationService.insert(userId, imei, 0, locationType, lat, lng,
												Integer.valueOf(0), statusType, new Timestamp(timestamp * 1000), 1,address);
									}
								}
							} else {
								sosDto.setLat(lat);
								sosDto.setLng(lng);
								sosDto.setLocationType(1);
								sosDto.setTimestamp(System.currentTimeMillis());
								locationService.insert(userId, imei, 1, locationType, lat, lng, Integer.valueOf(0),
										statusType, new Timestamp(timestamp * 1000), 1,address);
								String content = JSON.toJSONString(sosDto);
								PushUtil.pushMessage(target, titlee, content, 0);
							}

							// 电子围栏检查
							fenceService.checkFenceArea(userId, socketLoginDto.getImei(), lat, lng, timestamp * 1000);
							// 敏感区域检查
							sensitivePointService.checkSensitivePointArea(userId, socketLoginDto.getImei(), lat, lng,
									timestamp * 1000);
						} else {
							logger.warn("[LocationService]查询定位失败,status：" + status + ", info:" + info);
						}
					} else {
						logger.warn("[LocationService]高德返回应答为空!");
					}
				} catch (Exception e) {
					logger.warn("[LocationService]处理高德返回数据并保存数据发生异常:", e);
				}
			} else {
				logger.warn("[LocationService]错误基站位置信息，请通知设备端修正:" + jsonObject2.toJSONString());
			}

		}
		SocketBaseDto dto = new SocketBaseDto();
		dto.setType(jsonObject.getIntValue("type"));
		dto.setNo(jsonObject.getString("no"));
		dto.setTimestamp(new Date().getTime());
		dto.setStatus(0);
		/*FrequencyContro frequency = heartRateService.getHeartCheckFrequency("1");
		if (frequency != null) {
			dto.setFrequency(frequency.getFrequency_time());
		} else {
			dto.setFrequency(4);
		}*/
		return dto;

	}
}
