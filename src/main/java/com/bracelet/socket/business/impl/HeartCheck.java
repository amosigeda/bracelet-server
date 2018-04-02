package com.bracelet.socket.business.impl;

import io.netty.channel.Channel;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.bracelet.dto.SocketBaseDto;
import com.bracelet.dto.SocketLoginDto;
import com.bracelet.entity.FrequencyContro;
import com.bracelet.service.IHeartRateService;
import com.bracelet.service.IStepService;
import com.bracelet.socket.business.IService;

/**
 * 系统心跳
 * 
 */
@Component("heartCheck")
public class HeartCheck extends AbstractBizService {
//public class HeartCheck implements IService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	IHeartRateService heartRateService;
	@Autowired
	IStepService stepService;

	@Override
	protected SocketBaseDto process1(SocketLoginDto socketLoginDto, JSONObject jsonObject, Channel channel) {
		logger.info("===系统心跳：" + jsonObject.toJSONString());
		
		SocketBaseDto dto = new SocketBaseDto();
		dto.setType(jsonObject.getIntValue("type"));
		dto.setNo(jsonObject.getString("no"));
		dto.setTimestamp(new Date().getTime());
		dto.setStatus(0);
		FrequencyContro frequency=heartRateService.getHeartCheckFrequency("1");
		if(frequency!=null){
			dto.setFrequency(frequency.getFrequency_time());
		}else{
			dto.setFrequency(4);
		}
		return dto;
	}
	
	/*@Override
	public SocketBaseDto process(JSONObject jsonObject, Channel channel) {
		logger.info("===系统心跳：" + jsonObject.toJSONString());
		
		SocketBaseDto dto = new SocketBaseDto();
		dto.setType(jsonObject.getIntValue("type"));
		dto.setNo(jsonObject.getString("no"));
		dto.setTimestamp(new Date().getTime());
		dto.setStatus(0);
		FrequencyContro frequency=heartRateService.getHeartCheckFrequency("1");
		if(frequency!=null){
			dto.setFrequency(frequency.getFrequency_time());
		}else{
			dto.setFrequency(4);
		}
		return dto;
	}*/
	

	/*@Override
	protected SocketBaseDto process1(SocketLoginDto socketLoginDto, JSONObject jsonObject, Channel channel) {
		logger.info("===系统心跳：" + jsonObject.toJSONString());
		
		Long user_id = socketLoginDto.getUser_id();
		String imei = socketLoginDto.getImei();
		
		this.stepService.insert(user_id, imei, jsonObject.getInteger("stepNumber"), new Timestamp(jsonObject.getLongValue("timestamp") * 1000));
		
		SocketBaseDto dto = new SocketBaseDto();
		dto.setType(jsonObject.getIntValue("type"));
		dto.setNo(jsonObject.getString("no"));
		dto.setTimestamp(new Date().getTime());
		dto.setStatus(0);
		FrequencyContro frequency=heartRateService.getHeartCheckFrequency("1");
		if(frequency!=null){
			dto.setFrequency(frequency.getFrequency_time());
		}else{
			dto.setFrequency(4);
		}
		return dto;
		
	}
*/
}
