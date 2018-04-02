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

@Service("locationService")
public class LocationService extends AbstractBizService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	ILocationService locationService;
	@Autowired
	IFenceService fenceService;
	@Autowired
	ISensitivePointService sensitivePointService;
	@Autowired
	ITokenInfoService tokenInfoService;
	/*@Autowired
	IPushlogService pushlogService;*/

	/**
	 * 高德地图key，基站查询
	 */

	public SocketBaseDto process1(SocketLoginDto socketLoginDto, JSONObject jsonObject, Channel channel) {
		Long userId = socketLoginDto.getUser_id();
		String imei = socketLoginDto.getImei();
		SosDto sosDto = new SosDto();
		String target = tokenInfoService.getTokenByUserId(userId);
		String title = "设备定位";
		String notifyContent = "收到设备定位，请点击查看";

		// 0gps 1基站
		String locationType = jsonObject.getString("locationType");
		String lat = null; // 纬度
		String lng = null; // 经度
		String address=null;
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
								locationService.insert(user_id, imei, 1, locationType, locationsArr[1], locationsArr[0],
										accuracy, statusType, new Timestamp(timestamp * 1000), 1,address);

								sosDto.setLat(locationsArr[1]);
								sosDto.setLng(locationsArr[0]);
								sosDto.setLocationType(0);
								sosDto.setTimestamp(System.currentTimeMillis());
								String content = JSON.toJSONString(sosDto);
								PushUtil.push(target, title, content, notifyContent);

								//this.pushlogService.insert(userId, socketLoginDto.getImei(), 0, target, title, content);

								// 电子围栏检查
								fenceService.checkFenceArea(user_id, socketLoginDto.getImei(), locationsArr[1],
										locationsArr[0], timestamp * 1000);
								// 敏感区域检查
								sensitivePointService.checkSensitivePointArea(user_id, socketLoginDto.getImei(),
										locationsArr[1], locationsArr[0], timestamp * 1000);
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
		} else if ("1".equals(locationType)) {
			// 基站
			logger.info("[LocationService]处理基站位置信息：" + jsonObject.toJSONString());
			JSONArray jsonArray = jsonObject.getJSONArray("data");
			for (int i = 0; jsonArray != null && i < jsonArray.size(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.get(i);
				JSONObject lbsJsonObject = jsonObject2.getJSONObject("lbs");
				Long timestamp = jsonObject.getLong("timestamp");
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
								address=resultJsonObject.getString("desc");
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

								Location locationList = locationService.getLatest(user_id);
								Location showlocationList = locationService.getShowLatest(userId);
								if (locationList != null) {

									long lastTime = locationList.getUpload_time().getTime();// 上次时间
									long nowTime = System.currentTimeMillis() / 1000;
									Long s = (nowTime - lastTime / 1000) / 60;
									logger.info("现在的时间为=" + nowTime + ",上一次时间为=" + lastTime + ",计算出的时间差为=" + s);
									if (s >= 5) {
										// 大于五分钟必推送
										sosDto.setLat(lat);
										sosDto.setLng(lng);
										sosDto.setLocationType(1);
										sosDto.setTimestamp(System.currentTimeMillis());
										String content = JSON.toJSONString(sosDto);
										PushUtil.pushMessage(target, title, content, 0);
										// 接着判断显示点是否大于五分钟
										if (showlocationList != null) {
											lastTime = showlocationList.getUpload_time().getTime();// 上次点的显示时间
											s = (nowTime - lastTime / 1000) / 60;
											if (s >= 5) {
												locationService.insert(userId, imei, 1, locationType, lat, lng,
														Integer.valueOf(0), statusType, new Timestamp(timestamp * 1000),
														1,address);
											} else {
												double distance = Utils.calcDistance(Double.parseDouble(lng),
														Double.parseDouble(lat),
														Double.parseDouble(showlocationList.getLng()),
														Double.parseDouble(showlocationList.getLat()));
												if (distance > Utils.distance) {
													locationService.insert(userId, imei, 1, locationType, lat, lng,
															Integer.valueOf(0), statusType,
															new Timestamp(timestamp * 1000), 1,address);
												} else {
													locationService.insert(userId, imei, 1, locationType, lat, lng,
															Integer.valueOf(0), statusType,
															new Timestamp(timestamp * 1000), 0,address);
												}
											}
										} else {
											locationService.insert(userId, imei, 1, locationType, lat, lng,
													Integer.valueOf(0), statusType, new Timestamp(timestamp * 1000), 1,address);
										}

									} else {
										// 小于五分钟的
										if (showlocationList != null) {
											lastTime = showlocationList.getUpload_time().getTime();// 上次点的显示时间
											s = (nowTime - lastTime / 1000) / 60;
											if (s >= 5) {
												locationService.insert(userId, imei, 0, locationType, lat, lng,
														Integer.valueOf(0), statusType, new Timestamp(timestamp * 1000),
														1,address);
											} else {
												double distance = Utils.calcDistance(Double.parseDouble(lng),
														Double.parseDouble(lat),
														Double.parseDouble(showlocationList.getLng()),
														Double.parseDouble(showlocationList.getLat()));
												if (distance > Utils.distance) {
													locationService.insert(userId, imei, 0, locationType, lat, lng,
															Integer.valueOf(0), statusType,
															new Timestamp(timestamp * 1000), 1,address);
												} else {
													locationService.insert(userId, imei, 0, locationType, lat, lng,
															Integer.valueOf(0), statusType,
															new Timestamp(timestamp * 1000), 0,address);
												}
											}
										} else {
											locationService.insert(userId, imei, 0, locationType, lat, lng,
													Integer.valueOf(0), statusType, new Timestamp(timestamp * 1000), 1,address);
										}
									}

								} else {
									sosDto.setLat(lat);
									sosDto.setLng(lng);
									sosDto.setLocationType(1);
									sosDto.setTimestamp(System.currentTimeMillis());
									String content = JSON.toJSONString(sosDto);
									PushUtil.push(target, title, content, notifyContent);
								//	this.pushlogService.insert(userId, socketLoginDto.getImei(), 0, target, title,content);
									locationService.insert(user_id, imei, 1, locationType, lat, lng, Integer.valueOf(0),
											statusType, new Timestamp(timestamp * 1000), 1,address);
								}

								// 电子围栏检查
								fenceService.checkFenceArea(user_id, socketLoginDto.getImei(), lat, lng,
										timestamp * 1000);
								// 敏感区域检查
								sensitivePointService.checkSensitivePointArea(user_id, socketLoginDto.getImei(), lat,
										lng, timestamp * 1000);
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
		} else if ("2".equals(locationType)) {
			logger.info("[LocationService]处理wifi位置信息：" + jsonObject.toJSONString());
			JSONArray jsonArray = jsonObject.getJSONArray("data");
			for (int i = 0; jsonArray != null && i < jsonArray.size(); i++) {
				JSONObject jsonObject2 = (JSONObject) jsonArray.get(i);
				JSONObject wifiJsonObject = jsonObject2.getJSONObject("wifi");
				Long timestamp = jsonObject2.getLong("timestamp");

				String smac = wifiJsonObject.getString("smac");
				String mmac = wifiJsonObject.getString("mmac");
				String macs = wifiJsonObject.getString("macs");
				String serverip = wifiJsonObject.getString("serverip");
				LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
				map.put("accesstype", Utils.OneString);
				map.put("imei", imei);
				map.put("smac", smac);
				map.put("mmac", mmac);
				map.put("macs", macs);
				map.put("key", Utils.KEY);
				map.put("serverip", serverip);
				String jsonToString = HttpClientGet.sendGetToGaoDe(Utils.URL, map);
				logger.info("高德wifi返回数据=" + jsonToString);
				if (jsonToString != null) {
					JSONObject jsons = (JSONObject) JSON.parse(jsonToString);
					String status = jsons.getString("status");
					if ("1".equals(status)) {
						String results = jsons.getString("result");
						JSONObject jsonResult = (JSONObject) JSON.parse(results);
						String location = jsonResult.getString("location");
						String radius = jsonResult.getString("radius");
						if (location != null) {
							String[] locations = location.split(",");
							lng = locations[0];
							lat = locations[1];
							locationService.insert(user_id, imei, 0, locationType, lat, lng, Integer.valueOf(radius),
									statusType, new Timestamp(timestamp * 1000), 0,address);
						}
					}
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
