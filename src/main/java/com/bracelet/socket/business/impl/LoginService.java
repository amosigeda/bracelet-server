package com.bracelet.socket.business.impl;

import io.netty.channel.Channel;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bracelet.dto.SocketBaseDto;
import com.bracelet.dto.SocketLoginDto;
import com.bracelet.entity.UserInfo;
import com.bracelet.entity.WhiteListInfo;
import com.bracelet.entity.WhiteListRequest;
import com.bracelet.exception.BizException;
import com.bracelet.util.RespCode;
import com.bracelet.service.ISosService;
import com.bracelet.service.IUserInfoService;
import com.bracelet.socket.business.IService;
import com.bracelet.util.ChannelMap;
import com.bracelet.util.RanomUtil;

/**
 * 登录服务 {"type":1,"no":"1234567","timestamp":1501123709,"data":
 * {"dv":"divNo.1","sd":"sdV1"}}
 */
@Component("loginService")
public class LoginService implements IService {
	@Autowired
	IUserInfoService userInfoService;
	
	@Autowired
	ISosService sosService;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public SocketBaseDto process(JSONObject jsonObject, Channel channel) {
		JSONObject jsonObject2 = (JSONObject) jsonObject.get("data");
		if (jsonObject2 == null) {
			throw new BizException(RespCode.NOTEXIST_PARAM);
		}
		String dv = jsonObject2.getString("dv");
		String sd = jsonObject2.getString("sd");
		String no = jsonObject.getString("no");
		String imei = jsonObject.getString("imei");
		// Long timestamp = jsonObject.getLong("timestamp");
		logger.info("登录,dv:" + dv + ",sd:" + sd + ",no:" + no + ",imei:" + imei);

		UserInfo userInfo = userInfoService.getUserInfoByImei(imei);
		if (userInfo == null) {
			logger.info("未绑定的设备,imei:" + imei);
			throw new BizException(RespCode.DEV_NOT_EXIST);
		}

		SocketLoginDto channelDto = new SocketLoginDto();
		channelDto.setChannel(channel);
		channelDto.setNo(no);
		channelDto.setImei(imei);
		channelDto.setUser_id(userInfo.getUser_id());

		logger.info("保存手环登录信息,no:" + no + ",imei" + imei + ",user_id:" + userInfo.getUser_id());
		ChannelMap.addChannel(imei, channelDto);
		ChannelMap.addChannel(channel, channelDto);
		
		/*userInfoService.updateImeiStatus(userInfo.getUser_id(),1);*/

		SocketBaseDto dto = new SocketBaseDto();
		dto.setType(jsonObject.getIntValue("type"));
		dto.setNo(jsonObject.getString("no"));
		dto.setTimestamp(new Date().getTime());
		dto.setStatus(0);
		StringBuffer backSb = new StringBuffer();
			List<WhiteListInfo> list = sosService.find(userInfo.getUser_id());
			if (list != null) {
				for (WhiteListInfo wlInfo : list) {
					if (backSb.length() <= 0) {
						backSb.append(wlInfo.getPhone());
					} else {
						backSb.append(",");
						backSb.append(wlInfo.getPhone());
					}
				}
				dto.setData(backSb.toString());
			} else {
				dto.setData(backSb.toString());
				logger.info("设备登录，查询设备的白名单为空");
			}
		
		return dto;
	}
}
