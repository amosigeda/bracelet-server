package com.bracelet.socket.business.impl;

import io.netty.channel.Channel;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.bracelet.dto.SocketBaseDto;
import com.bracelet.dto.SocketLoginDto;
import com.bracelet.socket.business.IService;
import com.bracelet.util.ChannelMap;

/**
 * 设备关机
 * 
 */
@Component("shutDownService")
public class Shutdown extends AbstractBizService {
	private Logger logger = LoggerFactory.getLogger(getClass());


	@Override
	protected SocketBaseDto process1(SocketLoginDto socketLoginDto, JSONObject jsonObject, Channel channel) {
		logger.info("===设备关机：" + jsonObject.toJSONString());
		SocketBaseDto dto = new SocketBaseDto();
		dto.setType(jsonObject.getIntValue("type"));
		dto.setNo(jsonObject.getString("no"));
		dto.setTimestamp(new Date().getTime());
		dto.setStatus(0);
		String imei=socketLoginDto.getImei();
		ChannelMap.removeChannel(imei);
		ChannelMap.removeChannel(socketLoginDto.getChannel());
		socketLoginDto.getChannel().close();
		return dto;
	}

}
