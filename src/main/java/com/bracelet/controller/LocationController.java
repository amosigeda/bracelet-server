package com.bracelet.controller;

import com.alibaba.fastjson.JSON;
import com.bracelet.dto.HttpBaseDto;
import com.bracelet.dto.SocketLoginDto;
import com.bracelet.entity.FrequencyContro;
import com.bracelet.entity.Location;
import com.bracelet.entity.LocationModel;
import com.bracelet.entity.LocationPointc;
import com.bracelet.entity.LocationRequest;
import com.bracelet.entity.Step;
import com.bracelet.entity.UserInfo;
import com.bracelet.exception.BizException;
import com.bracelet.service.IFenceService;
import com.bracelet.service.ILocationService;
import com.bracelet.service.IStepService;
import com.bracelet.service.IUserInfoService;
import com.bracelet.socket.BaseChannelHandler;
import com.bracelet.util.ChannelMap;
import com.bracelet.util.RanomUtil;
import com.bracelet.util.RespCode;
import com.bracelet.util.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/location")
public class LocationController extends BaseController {

	@Autowired
	ILocationService locationService;
	@Autowired
	IUserInfoService userInfoService;
	@Autowired
	IStepService stepService;
	@Autowired
	IFenceService fenceService;
	@Resource
	BaseChannelHandler baseChannelHandler;
	private Logger logger = LoggerFactory.getLogger(getClass());

	@ResponseBody
	@RequestMapping(value = "/search/latest/{token}", method = RequestMethod.GET)
	public HttpBaseDto getLatestLocation(@PathVariable String token) {
		Long user_id = checkTokenAndUser(token);
		Location location = locationService.getLatest(user_id);
		Map<String, Object> dataMap = new HashMap<>();
		if (location != null) {
			dataMap.put("lat", location.getLat());
			dataMap.put("lng", location.getLng());
			dataMap.put("locationType", location.getLocation_type());
			dataMap.put("timestamp", location.getUpload_time().getTime());
		}
		Step step = stepService.getLatest(user_id);
		if (step != null) {
			dataMap.put("step", step.getStep_number());
		}
		HttpBaseDto dto = new HttpBaseDto();
		dto.setData(dataMap);
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "/search/realtime/{token}", method = RequestMethod.GET)
	public HttpBaseDto getRealtimeLocation(@PathVariable String token) {
		Long user_id = checkTokenAndUser(token);
		Location location = locationService.getRealtimeLocation(user_id, Integer.valueOf(1));
		Map<String, Object> dataMap = new HashMap<>();
		if (location != null) {
			dataMap.put("lat", location.getLat());
			dataMap.put("lng", location.getLng());
			dataMap.put("locationType", location.getLocation_type());
			dataMap.put("timestamp", location.getUpload_time().getTime());
		}

		Step step = stepService.getLatest(user_id);
		if (step != null) {
			dataMap.put("step", step.getStep_number());
		}
		HttpBaseDto dto = new HttpBaseDto();
		dto.setData(dataMap);
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "/ask/location/{token}", method = RequestMethod.GET)
	public HttpBaseDto askLocation(@PathVariable String token) {
		Long user_id = checkTokenAndUser(token);
		UserInfo userInfo = userInfoService.getUserInfoById(user_id);
		if (userInfo == null) {
			logger.info("askLocation error.no login.token:" + token);
			throw new BizException(RespCode.U_NOT_EXIST);
		}
		SocketLoginDto socketLoginDto = ChannelMap.getChannel(userInfo.getImei());
		if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
			logger.info("socketLoginDto error.no login.token:" + token);
			throw new BizException(RespCode.DEV_NOT_LOGIN);
		}

		LocationRequest re = new LocationRequest();
		re.setA(0);
		re.setTimestamp(System.currentTimeMillis() / 1000);
		re.setType(30);
		re.setNo(RanomUtil.getFixLenthString(10));

		if (socketLoginDto.getChannel().isActive()) {
			socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
			logger.info("===request getLocation...ip:" + socketLoginDto.getChannel().remoteAddress().toString()
					+ ",data:" + JSON.toJSONString(re));
		} else {
			logger.info("socketLoginDto error.no login.not active.token:" + token);
			throw new BizException(RespCode.DEV_NOT_LOGIN);
		}

		HttpBaseDto dto = new HttpBaseDto();
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "/search/footprint/{token}", method = RequestMethod.GET)
	public HttpBaseDto getLocationFootprint(@PathVariable String token,
			@RequestParam(value = "type", required = false) String type) {
		Long user_id = checkTokenAndUser(token);
		List<Location> locationList = locationService.getFootprint(user_id, type, "1");
		Map<String, Object> dataMap = new HashMap<>();
		List<Map<String, Object>> locDataList = new LinkedList<Map<String, Object>>();
		if (locationList != null) {
			for (Location location : locationList) {
				Map<String, Object> locDataMap = new HashMap<>();
				locDataMap.put("lat", location.getLat());
				locDataMap.put("lng", location.getLng());
				locDataMap.put("locationType", location.getLocation_type());
				locDataMap.put("timestamp", location.getUpload_time().getTime());
				locDataList.add(locDataMap);
			}
		}
		dataMap.put("location", locDataList);
		Step step = stepService.getLatest(user_id);
		if (step != null) {
			dataMap.put("step", step.getStep_number());
		}
		HttpBaseDto dto = new HttpBaseDto();
		dto.setData(dataMap);
		return dto;

	}

	@ResponseBody
	@RequestMapping(value = "/setlocationMode", method = RequestMethod.POST)
	public HttpBaseDto setLocationMode(@RequestParam String token, @RequestParam Integer type) {
		logger.info("设置定位频率token=" + token + ",type=" + type);
		Long user_id = checkTokenAndUser(token);
		UserInfo userInfo = userInfoService.getUserInfoById(user_id);
		if (userInfo == null) {
			logger.info("askLocation error.no login.token:" + token);
			throw new BizException(RespCode.U_NOT_EXIST);
		}
		SocketLoginDto socketLoginDto = ChannelMap.getChannel(userInfo.getImei());
		if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
			logger.info("socketLoginDto error.no login.token:" + token);
			throw new BizException(RespCode.DEV_NOT_LOGIN);
		}
		if (socketLoginDto.getChannel().isActive()) {
			LocationRequest re = new LocationRequest();
			re.setA(1);
			re.setTimestamp(System.currentTimeMillis() / 1000);
			re.setType(33);
			re.setNo(RanomUtil.getFixLenthString(10));
			FrequencyContro frequency = locationService.getLocationCheckFrequency(type);
			if (frequency != null) {
				re.setFrequency(frequency.getFrequency_time());
			} else {
				// 1 非常精准 2普通 3精准
				if (type == 1) {
					re.setFrequency(1);
				} else if (type == 2) {
					re.setFrequency(5);
				} else if (type == 3) {
					re.setFrequency(10);
				}
			}
			socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
			logger.info("设置设备定位频率:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
					+ JSON.toJSONString(re));
			LocationModel locationModel=locationService.getLastModel(user_id);
			if(locationModel!=null){
				locationService.updateLocationModel(locationModel.getId(),type);
			}else{
				locationService.insertLocationModel(user_id,userInfo.getImei(), type);
			}
			
		} else {
			logger.info("socketLoginDto error.no login.not active.token:" + token);
			throw new BizException(RespCode.DEV_NOT_LOGIN);
		}

		HttpBaseDto dto = new HttpBaseDto();
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "/guiji", method = RequestMethod.POST)
	public HttpBaseDto guiJi(@RequestParam String token, @RequestParam Integer type,@RequestParam String name) {
		logger.info("让设备开始走圈token=" + token + ",type=" + type);
		Long user_id = checkTokenAndUser(token);
		UserInfo userInfo = userInfoService.getUserInfoById(user_id);
		if (userInfo == null) {
			logger.info("askLocation error.no login.token:" + token);
			throw new BizException(RespCode.U_NOT_EXIST);
		}
		SocketLoginDto socketLoginDto = ChannelMap.getChannel(userInfo.getImei());
		if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
			logger.info("socketLoginDto error.no login.token:" + token);
			throw new BizException(RespCode.DEV_NOT_LOGIN);
		}
		if (socketLoginDto.getChannel().isActive()) {
			LocationRequest re = new LocationRequest();
			re.setA(1);
			re.setTimestamp(System.currentTimeMillis() / 1000);
			if (type == 1) {
				re.setType(34);
			} else if (type == 2) {
				re.setType(35);
			}
			re.setNo(RanomUtil.getFixLenthString(10));
			socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
			if (type == 1) {
				LocationPointc lpc = locationService.getLatestC(user_id);
				if (lpc != null) {
					locationService.insert(user_id, type);
				} else {
					throw new BizException(RespCode.CC_DEVICE_point);
				}
			} else if (type == 2) {
				// 查询出开始时间。查询之间的点。汇总起来插入到数据库就可以了
				LocationPointc lpc = locationService.getLatestC(user_id);
				if (lpc != null) {
					Long id = lpc.getId();
					String startTime = Utils.date2Str(lpc.getCreatetime());
					String endTime = Utils.date2Str(new Date());
					List<Location> locationList = locationService.getFootPointPrint(user_id, 0, 3, startTime, endTime);
					StringBuffer backSb = new StringBuffer();
					if (locationList.size() > 0) {
						for (Location location : locationList) {
							if (backSb.length() <= 0) {
								backSb.append(location.getLat());
								backSb.append(",");
								backSb.append(location.getLng());
							} else {
								backSb.append(",");
								backSb.append(location.getLat());
								backSb.append(",");
								backSb.append(location.getLng());
							}
						}
					}
					this.fenceService.insertOddShape(user_id, backSb.toString(), name, "2");

					locationService.deleteLatestC(id, user_id);
				}
			}
			logger.info("设置设备开始走圈:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
					+ JSON.toJSONString(re));
		} else {
			logger.info("socketLoginDto error.no login.not active.token:" + token);
			throw new BizException(RespCode.DEV_NOT_LOGIN);
		}

		HttpBaseDto dto = new HttpBaseDto();
		return dto;
	}
	
	@ResponseBody
	@RequestMapping(value = "/search/locationModel/{token}", method = RequestMethod.GET)
	public HttpBaseDto searchLocationModel(@PathVariable String token) {
		Long user_id = checkTokenAndUser(token);
		UserInfo userInfo = userInfoService.getUserInfoById(user_id);
		if (userInfo == null) {
			logger.info("askLocation error.no login.token:" + token);
			throw new BizException(RespCode.U_NOT_EXIST);
		}
		Map<String, Object> dataMap = new HashMap<>();
		LocationModel locationModel=locationService.getLastModel(user_id);
		if(locationModel!=null){
			dataMap.put("type", locationModel.getType());
			dataMap.put("create_time", locationModel.getCreate_time().getTime());
			dataMap.put("update_time", locationModel.getUpdate_time().getTime());
			locationService.insertLocationModel(user_id,"1234567", 5);
		}
		HttpBaseDto dto = new HttpBaseDto();
        dto.setData(dataMap);
        //{"code":0,"message":"操作成功！","data":{"update_time":1511838336000,"create_time":1511838010000,"type":1}}
		return dto;
	}

}
