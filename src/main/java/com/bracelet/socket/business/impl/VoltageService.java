package com.bracelet.socket.business.impl;

import io.netty.channel.Channel;

import java.sql.Timestamp;
import java.util.Date;

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
import com.bracelet.service.ITokenInfoService;
import com.bracelet.service.IVoltageService;
import com.bracelet.util.PushUtil;

/**
 * 电压业务
 * 
 */
@Component("voltageService")
public class VoltageService extends AbstractBizService{
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    IVoltageService voltageService;
    
    @Autowired
	ITokenInfoService tokenInfoService;

    public SocketBaseDto process1(SocketLoginDto socketLoginDto, JSONObject jsonObject, Channel channel) {
    	String imei = socketLoginDto.getImei();
    	VoltageDto voltageDto = new VoltageDto();
    	String title = "设备低电量提醒";
    	String notifyContent = "设备低电量提醒，请点击查看";
    	
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        logger.info("===电压：" + jsonObject.toJSONString());
        Long user_id = socketLoginDto.getUser_id();
        for (int i = 0; i < jsonArray.size(); i++) {
            try {
                JSONObject jsonObject2 = (JSONObject) jsonArray.get(i);
                long timestamp = jsonObject2.getLongValue("timestamp");
                Integer voltage = jsonObject2.getInteger("voltage");
                voltageService.insert(user_id, imei,voltage,  new Timestamp(timestamp * 1000));
            	if(voltage<=20){
            		Long userId = socketLoginDto.getUser_id();
            		String target = tokenInfoService.getTokenByUserId(userId);
            		voltageDto.setVoltage(voltage);
            		voltageDto.setTimestamp(System.currentTimeMillis());
					String content = JSON.toJSONString(voltageDto);
					PushUtil.push(target, title, content, notifyContent);
            	}
            } catch (Exception e) {
                logger.error("保存电压数组数据，发生错误:" + jsonArray.toJSONString(), e);
            }
        }

        SocketBaseDto dto = new SocketBaseDto();
        dto.setType(jsonObject.getIntValue("type"));
        dto.setNo(jsonObject.getString("no"));
        dto.setTimestamp(new Date().getTime());
        dto.setStatus(0);
        return dto;
    }
}
