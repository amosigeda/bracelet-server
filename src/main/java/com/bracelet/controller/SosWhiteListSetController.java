package com.bracelet.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bracelet.dto.HttpBaseDto;
import com.bracelet.dto.SocketLoginDto;
import com.bracelet.entity.LocationRequest;
import com.bracelet.entity.UserInfo;
import com.bracelet.entity.WhiteListInfo;
import com.bracelet.entity.WhiteListRequest;
import com.bracelet.exception.BizException;
import com.bracelet.service.ISosService;
import com.bracelet.service.IUserInfoService;
import com.bracelet.util.ChannelMap;
import com.bracelet.util.RanomUtil;
import com.bracelet.util.RespCode;
import com.bracelet.util.StringUtil;
/*
 * 新增文件 app 服务器接口 
 * 白名单三个接口
 * 添加
 * 删除
 * 查询
 * */

@Controller
@RequestMapping("/sos")
public class SosWhiteListSetController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	ISosService sosService;

	@Autowired
	IUserInfoService userInfoService;

	@ResponseBody
	@RequestMapping(value = "/whitelist", method = RequestMethod.POST)
	public HttpBaseDto setWhiteList(@RequestParam String token, @RequestParam String phone, @RequestParam String name) {
		logger.info("增加白名单="+token);
		Long user_id = checkTokenAndUser(token);
		if (StringUtils.isAnyEmpty(phone, name)) {
			throw new BizException(RespCode.NOTEXIST_PARAM);
		}
		if (StringUtil.isPhoneLegal(phone) != true) {
			logger.info("增加白名单名字:电话号码不合法="+phone);
			throw new BizException(RespCode.PHONE_INVALID);
		}
		WhiteListInfo wtlist = sosService.getByPhone(user_id, phone);
		if(wtlist != null){
			throw new BizException(RespCode.WL_ALREADY_EXIT);
		}
		sosService.insert(user_id, phone, name);

		UserInfo userInfo = userInfoService.getUserInfoById(user_id);
		SocketLoginDto socketLoginDto = ChannelMap.getChannel(userInfo.getImei());
		
		if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
			logger.info("socketLoginDto error.no login.token:" + token);
			throw new BizException(RespCode.WL_DEVICE_OFFLINE);
		}
		if (socketLoginDto.getChannel().isActive()) {
			logger.info("增加白名单-设备在线");
			StringBuffer backSb = new StringBuffer();
			List<WhiteListInfo> list = sosService.find(user_id);
			if (list.size()>0) {
				for (WhiteListInfo wlInfo : list) {
					if (backSb.length() <= 0) {
						backSb.append(wlInfo.getPhone());
					} else {
						backSb.append(",");
						backSb.append(wlInfo.getPhone());
					}
				}
				WhiteListRequest re = new WhiteListRequest();
				re.setA(0);
				re.setTimestamp(System.currentTimeMillis() / 1000);
				re.setType(25);
				re.setNo(RanomUtil.getFixLenthString(10));
				re.setData(backSb.toString());
				socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
				logger.info("===request getwhitelist..:" + socketLoginDto.getChannel().remoteAddress().toString()
						+ ",data:" + JSON.toJSONString(re));
				

			}
		} else {
			logger.info("设备：" + userInfo.getImei() + "设置白名单设备不在线--");
			throw new BizException(RespCode.WL_DEVICE_OFFLINE);
		}

		HttpBaseDto dto = new HttpBaseDto();
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "/whitelist/del", method = RequestMethod.POST)
	public HttpBaseDto deleteWhiteList(@RequestParam String token, @RequestParam Long id) {
		logger.info("删除白名单=" + token);
		Long user_id = checkTokenAndUser(token);
		sosService.delete(user_id, id);
		UserInfo userInfo = userInfoService.getUserInfoById(user_id);
		SocketLoginDto socketLoginDto = ChannelMap.getChannel(userInfo.getImei());
		if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
			logger.info("socketLoginDto error.no login.token:" + token);
			throw new BizException(RespCode.WL_DEVICE_OFFLINE);
		}
		if (socketLoginDto.getChannel().isActive()) {
			StringBuffer backSb = new StringBuffer();
			List<WhiteListInfo> list = sosService.find(user_id);
			if (list.size()>0) {
				for (WhiteListInfo wlInfo : list) {
					if (backSb.length() <= 0) {
						backSb.append(wlInfo.getPhone());
					} else {
						backSb.append(",");
						backSb.append(wlInfo.getPhone());
					}
				}
				WhiteListRequest re = new WhiteListRequest();
				re.setA(0);
				re.setTimestamp(System.currentTimeMillis() / 1000);
				re.setType(25);
				re.setNo(RanomUtil.getFixLenthString(10));
				re.setData(backSb.toString());
				socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(re) + "\r\n");
			}
		} else {
			logger.info("删除白名单--设备：" + userInfo.getImei() + "设置白名单设备不在线--");
			throw new BizException(RespCode.WL_DEVICE_OFFLINE);
		}

		HttpBaseDto dto = new HttpBaseDto();
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "/whitelist/{token}", method = RequestMethod.GET)
	public HttpBaseDto selectWhiteList(@PathVariable String token) {
		logger.info("查找白名单=" + token);
		Long user_id = checkTokenAndUser(token);
		List<WhiteListInfo> list = sosService.find(user_id);
		List<Map<String, Object>> datalist = new LinkedList<Map<String, Object>>();
		if (list != null) {
			for (WhiteListInfo wlInfo : list) {
				Map<String, Object> dataMap = new HashMap<>();
				dataMap.put("id", wlInfo.getId());
				dataMap.put("phone", wlInfo.getPhone());
				dataMap.put("name", wlInfo.getName());
				datalist.add(dataMap);
			}
		}
		HttpBaseDto dto = new HttpBaseDto();
		dto.setData(datalist);
		return dto;
	}
}
