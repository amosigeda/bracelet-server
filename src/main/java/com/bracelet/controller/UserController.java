package com.bracelet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bracelet.dto.HttpBaseDto;
import com.bracelet.dto.SocketLoginDto;
import com.bracelet.entity.UserInfo;
import com.bracelet.entity.Voltage;
import com.bracelet.exception.BizException;
import com.bracelet.service.IAuthcodeService;
import com.bracelet.service.IUserInfoService;
import com.bracelet.service.IVoltageService;
import com.bracelet.util.ChannelMap;
import com.bracelet.util.RanomUtil;
import com.bracelet.util.RespCode;
import com.bracelet.util.Utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	IUserInfoService userInfoService;
	@Autowired
	IAuthcodeService authcodeService;

	@Autowired
	IVoltageService voltageService;
	private Logger logger = LoggerFactory.getLogger(getClass());

	@ResponseBody
	@RequestMapping(value = "/getAuthCode/{tel}", method = RequestMethod.GET)
	public HttpBaseDto getAuthCode(@PathVariable String tel) {
		if (StringUtils.isEmpty(tel)) {
			throw new BizException(RespCode.NOTEXIST_PARAM);
		}
		this.authcodeService.sendAuthCode(tel);
		HttpBaseDto dto = new HttpBaseDto();
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public HttpBaseDto register(@RequestParam String tel, @RequestParam String code, @RequestParam String password) {
		if (StringUtils.isAnyEmpty(tel, code,password)) {
			throw new BizException(RespCode.NOTEXIST_PARAM);
		}
		if (this.authcodeService.verifyAuthCode(tel, code)) {
			UserInfo userInfo = userInfoService.getUserInfoByUsername(tel);
			if (userInfo != null) {
				logger.info("该手机号已经注册, tel:" + tel);
				throw new BizException(RespCode.U_ALREADY_REGED);
			}
			if (this.userInfoService.insert(tel,Utils.getMD5(password))) {
				UserInfo savedObj = userInfoService.getUserInfoByUsername(tel);
				String token = this.tokenInfoService.genToken(savedObj.getUser_id());
				HttpBaseDto dto = new HttpBaseDto();
				Map<String, Object> dataMap = new HashMap<>();
				dataMap.put("id", savedObj.getUser_id());
				dataMap.put("username", savedObj.getUsername());
				dataMap.put("imei", savedObj.getImei());
				dataMap.put("token", token);
				dto.setData(dataMap);
				return dto;
			} else {
				logger.info("用户注册保存数据库失败, tel:" + tel);
				throw new BizException(RespCode.SYS_ERR);
			}
		} else {
			// 验证码错误
			logger.info("验证码验证失败, tel:" + tel + ",code:" + code);
			throw new BizException(RespCode.U_AUTHCODE_NOTEXIST);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/forgetpwd", method = RequestMethod.POST)
	public HttpBaseDto forgetpwd(@RequestParam String tel, @RequestParam String code, @RequestParam String password) {
		if (StringUtils.isAnyEmpty(tel, code, password)) {
			throw new BizException(RespCode.NOTEXIST_PARAM);
		}
		if (this.authcodeService.verifyAuthCode(tel, code)) {
			UserInfo userInfo = userInfoService.getUserInfoByUsername(tel);
			if (userInfo == null) {
				logger.info("该手机号尚未注册, tel:" + tel);
				throw new BizException(RespCode.U_TEL_NOT_REGED);
			}
			if (this.userInfoService.saveUserPassword(userInfo.getUser_id(), Utils.getMD5(password))) {
				HttpBaseDto dto = new HttpBaseDto();
				return dto;
			} else {
				logger.info("用户修改密码数据，发生数据库失败, tel:" + tel + ",password:" + password);
				throw new BizException(RespCode.SYS_ERR);
			}
		} else {
			// 验证码错误
			logger.info("修改密码验证码验证失败, tel:" + tel + ",code:" + code);
			throw new BizException(RespCode.U_AUTHCODE_NOTEXIST);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/register/pwd", method = RequestMethod.POST)
	public HttpBaseDto registerPwd(@RequestParam String token, @RequestParam String password) {
		if (StringUtils.isAnyEmpty(password)) {
			throw new BizException(RespCode.NOTEXIST_PARAM);
		}
		Long user_id = checkTokenAndUser(token);
		if (this.userInfoService.saveUserPassword(user_id, Utils.getMD5(password))) {
			HttpBaseDto dto = new HttpBaseDto();
			return dto;
		} else {
			logger.info("保存用户密码数据，发生数据库失败, token:" + token + ",password:" + password);
			throw new BizException(RespCode.SYS_ERR);
		}
	}

	/**
	 * type: 0: 验证码， 1: 密码
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public HttpBaseDto login(@RequestParam String tel, @RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "password", required = false) String password) {
		if (StringUtils.isAnyEmpty(tel)) {
			throw new BizException(RespCode.NOTEXIST_PARAM);
		}
		if (StringUtils.isEmpty(type)) {
			type = "0";
		}
		if ("0".equals(type)) {
			if (StringUtils.isAnyEmpty(code)) {
				throw new BizException(RespCode.NOTEXIST_PARAM);
			}
			if (this.authcodeService.verifyAuthCode(tel, code)) {
				UserInfo userInfo = userInfoService.getUserInfoByUsername(tel);
				if (userInfo == null) {
					logger.info("该手机号尚未注册, tel:" + tel);
					throw new BizException(RespCode.U_TEL_NOT_REGED);
				}
				String token = this.tokenInfoService.genToken(userInfo.getUser_id());
				HttpBaseDto dto = new HttpBaseDto();
				Map<String, Object> dataMap = new HashMap<>();
				dataMap.put("type", 1);
				dataMap.put("id", userInfo.getUser_id());
				dataMap.put("username", userInfo.getUsername());
				dataMap.put("imei", userInfo.getImei());
				dataMap.put("token", token);
				dataMap.put("nickname", userInfo.getNickname());
				dataMap.put("height", userInfo.getHeight());
				dataMap.put("weight", userInfo.getWeight());
				dataMap.put("sex", userInfo.getSex());
				dataMap.put("avatar", userInfo.getAvatar());
				dataMap.put("address", userInfo.getAddress());
				dto.setData(dataMap);
				return dto;
			} else {
				// 验证码错误
				logger.info("验证码验证失败, tel:" + tel + ",code:" + code);
				throw new BizException(RespCode.U_AUTHCODE_NOTEXIST);
			}
		} else if ("1".equals(type)) {
			if (StringUtils.isAnyEmpty(password)) {
				throw new BizException(RespCode.NOTEXIST_PARAM);
			}
			UserInfo userInfo = userInfoService.getUserInfoByUsername(tel);
			if (userInfo == null) {
				logger.info("该手机号尚未注册, tel:" + tel);
				throw new BizException(RespCode.U_TEL_NOT_REGED);
			}
			String genPassword = Utils.getMD5(password);
			if (genPassword.equalsIgnoreCase(userInfo.getPassword())) {
				String token = this.tokenInfoService.genToken(userInfo.getUser_id());
				HttpBaseDto dto = new HttpBaseDto();
				Map<String, Object> dataMap = new HashMap<>();
				dataMap.put("type", 1);
				dataMap.put("id", userInfo.getUser_id());
				dataMap.put("username", userInfo.getUsername());
				dataMap.put("imei", userInfo.getImei());
				dataMap.put("token", token);
				dataMap.put("nickname", userInfo.getNickname());
				dataMap.put("height", userInfo.getHeight());
				dataMap.put("weight", userInfo.getWeight());
				dataMap.put("sex", userInfo.getSex());
				dataMap.put("avatar", userInfo.getAvatar());
				dataMap.put("address", userInfo.getAddress());
				dto.setData(dataMap);
				return dto;
			} else {
				logger.info("用户密码错误, tel:" + tel + ",password:" + password + ",genPassword:" + genPassword + ",userPwd:"
						+ userInfo.getPassword());
				throw new BizException(RespCode.U_USERNAME_PWD_ERR);
			}
		} else {
			throw new BizException(RespCode.NOTEXIST_PARAM);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/saveUserInfo", method = RequestMethod.POST)
	public HttpBaseDto saveUserInfo(@RequestParam String token, @RequestParam String avatar,
			@RequestParam String nickname, @RequestParam String sex, @RequestParam String weight,
			@RequestParam String height, @RequestParam(value = "address", required = false) String address) {
		if (StringUtils.isAnyEmpty(avatar, nickname, sex, weight, height)) {
			throw new BizException(RespCode.NOTEXIST_PARAM);
		}
		Long user_id = checkTokenAndUser(token);
		Integer intSex = Integer.parseInt(sex);
		if (this.userInfoService.updateUserInfo(user_id, avatar, nickname, intSex, weight, height, address)) {
			HttpBaseDto dto = new HttpBaseDto();
			return dto;
		} else {
			logger.info("更新用户数据，发生数据库失败, token:" + token + ",avatar:" + avatar + ",nickname:" + nickname + ",sex:" + sex
					+ ",weight:" + weight + ",height:" + height + ",address:" + address);
			throw new BizException(RespCode.SYS_ERR);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/info/{token}", method = RequestMethod.GET)
	public HttpBaseDto getInfo(@PathVariable String token) {
		Long user_id = checkTokenAndUser(token);
		UserInfo userInfo = userInfoService.getUserInfoById(user_id);
		if (userInfo == null) {
			logger.info("获取用户信息，token对应的user不存在 token:" + token + ",userId:" + user_id);
			throw new BizException(RespCode.U_NOT_EXIST);
		}
		HttpBaseDto dto = new HttpBaseDto();
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("id", userInfo.getUser_id());
		dataMap.put("username", userInfo.getUsername());
		dataMap.put("avatar", userInfo.getAvatar());
		dataMap.put("nickname", userInfo.getNickname());
		dataMap.put("sex", userInfo.getSex());
		dataMap.put("weight", userInfo.getWeight());
		dataMap.put("height", userInfo.getHeight());
		dataMap.put("address", userInfo.getAddress());
		dataMap.put("dv", userInfo.getDv());
		dataMap.put("sd", userInfo.getSd());
		dataMap.put("imei", userInfo.getImei());
		dataMap.put("createtime", userInfo.getCreatetime() != null ? userInfo.getCreatetime().getTime() : 0);
		dataMap.put("bindingtime", userInfo.getBindingtime() != null ? userInfo.getBindingtime().getTime() : 0);
		dto.setData(dataMap);
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "/device/{token}", method = RequestMethod.GET)
	public HttpBaseDto getDevice(@PathVariable String token) {
		Long user_id = checkTokenAndUser(token);
		UserInfo userInfo = userInfoService.getUserInfoById(user_id);
		if (userInfo == null) {
			logger.info("获取用户设备，token对应的user不存在 token:" + token + ",userId:" + user_id);
			throw new BizException(RespCode.U_NOT_EXIST);
		}
		HttpBaseDto dto = new HttpBaseDto();
		Map<String, Object> dataMap = new HashMap<>();
		if (StringUtils.isNotEmpty(userInfo.getImei())) {
			dataMap.put("imei", userInfo.getImei());
			dataMap.put("dv", userInfo.getDv());
			dataMap.put("sd", userInfo.getSd());
			dataMap.put("bindingtime", userInfo.getBindingtime() != null ? userInfo.getBindingtime().getTime() : 0);
			dataMap.put("status", "在线");
			SocketLoginDto socketLoginDto = ChannelMap.getChannel(userInfo.getImei());
			if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
				dataMap.put("status", "离线");
			}
			Voltage voltage = voltageService.getLatest(user_id);
			if (voltage != null) {
				dataMap.put("voltage", voltage.getVoltage());
			} else {
				dataMap.put("voltage", 100);
			}
		}
		dto.setData(dataMap);
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "/device/bind", method = RequestMethod.POST)
	public HttpBaseDto deviceBind(@RequestParam String token, @RequestParam String imei) {
		if (StringUtils.isEmpty(imei)) {
			throw new BizException(RespCode.NOTEXIST_PARAM);
		}
		Long user_id = checkTokenAndUser(token);
		if (this.userInfoService.bindDevice(user_id, imei)) {
			HttpBaseDto dto = new HttpBaseDto();
			return dto;
		} else {
			logger.info("用户绑定设备失败, token:" + token + ",userId:" + user_id + ",imei:" + imei);
			throw new BizException(RespCode.U_BINGDING_ERR);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/device/unbind", method = RequestMethod.POST)
	public HttpBaseDto deviceUnbind(@RequestParam String token, @RequestParam String imei) {
		if (StringUtils.isEmpty(imei)) {
			throw new BizException(RespCode.NOTEXIST_PARAM);
		}
		Long user_id = checkTokenAndUser(token);
		if (this.userInfoService.unbindDevice(user_id, imei)) {
			HttpBaseDto dto = new HttpBaseDto();
			return dto;
		} else {
			logger.info("用户解除绑定设备失败, token:" + token + ",userId:" + user_id + ",imei:" + imei);
			throw new BizException(RespCode.SYS_ERR);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/ask/device/{token}", method = RequestMethod.GET)
	public HttpBaseDto askDevice(@PathVariable String token) {
		Long user_id = checkTokenAndUser(token);
		UserInfo userInfo = userInfoService.getUserInfoById(user_id);
		if (userInfo == null) {
			logger.info("askDevice error.no login.token:" + token);
			throw new BizException(RespCode.U_NOT_EXIST);
		}
		if (userInfo.getImei() == null||"".equals(userInfo.getImei())) {
			logger.info("app未绑定设备.token:" + token);
			throw new BizException(RespCode.U_APP_UNBINDDEVICE);
		}
		SocketLoginDto socketLoginDto = ChannelMap.getChannel(userInfo.getImei());
		if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
			logger.info("socketLoginDto error.no login.token:" + token);
			throw new BizException(RespCode.DEV_NOT_LOGIN);
		}

		Map<String, Object> req = new HashMap<String, Object>();
		req.put("a", 0);
		req.put("type", 31);
		req.put("no", RanomUtil.getFixLenthString(10));
		req.put("timestamp", System.currentTimeMillis() / 1000);
		if (socketLoginDto.getChannel().isActive()) {
			socketLoginDto.getChannel().writeAndFlush(JSON.toJSONString(req) + "\r\n");
			logger.info("===request askDevice...ip:" + socketLoginDto.getChannel().remoteAddress().toString() + ",data:"
					+ JSON.toJSONString(req));
		} else {
			logger.info("socketLoginDto error.no login.not active.token:" + token);
			throw new BizException(RespCode.DEV_NOT_LOGIN);
		}

		HttpBaseDto dto = new HttpBaseDto();
		return dto;
	}

	@ResponseBody
	@RequestMapping(value = "/weixin", method = RequestMethod.POST)
	public HttpBaseDto WeiXinAuthService(@RequestParam String access_token, @RequestParam String openid) {
		// 获取授权 access_token
		/*
		 * StringBuffer loginUrl = new StringBuffer();
		 * loginUrl.append(Utils.WX_AUTH_LOGIN_URL).append("?appid=").append(
		 * Utils.WX_APP_ID).append("&secret=")
		 * .append(Utils.WX_APP_KEY).append("&code=").append(code).append(
		 * "&grant_type=authorization_code"); String loginRet =
		 * Utils.get(loginUrl.toString()); JSONObject grantObj = (JSONObject)
		 * JSON.parse(loginRet); String errcode = grantObj.getString("errcode");
		 * if (!StringUtils.isEmpty(errcode)) {
		 * logger.error("login weixin error" + loginRet); throw new
		 * BizException(RespCode.WECHAT_LOGIN_ERR); } String openId =
		 * grantObj.getString("openid"); if (StringUtils.isEmpty(openId)) {
		 * logger.error("login weixin getOpenId error" + loginRet); } else {
		 * String accessToken = grantObj.getString("access_token"); String
		 * expiresIn = grantObj.getString("expires_in"); String refreshToken =
		 * grantObj.getString("refresh_token"); String scope =
		 * grantObj.getString("scope"); if (StringUtils.isEmpty(accessToken)) {
		 * logger.error("login weixin accessToken error" + accessToken); } else
		 * {
		 */
		// 获取用户信息
		HttpBaseDto dto = new HttpBaseDto();
		UserInfo userInfo = userInfoService.getUserInfoByUsername(openid);
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("type", 2);
		
		if (userInfo != null) {
			String token = this.tokenInfoService.genToken(userInfo.getUser_id());
			
			dataMap.put("id", userInfo.getUser_id());
			dataMap.put("username", userInfo.getUsername());
			dataMap.put("imei", userInfo.getImei());
			dataMap.put("token", token);
			dataMap.put("nickname", userInfo.getNickname());
			dataMap.put("height", userInfo.getHeight());
			dataMap.put("weight", userInfo.getWeight());
			dataMap.put("sex", userInfo.getSex());
			dataMap.put("avatar", userInfo.getAvatar());
			dataMap.put("address", userInfo.getAddress());

		} else {
			StringBuffer userUrl = new StringBuffer();
			userUrl.append(Utils.WX_USERINFO_URL).append("?access_token=").append(access_token).append("&openid=")
					.append(openid);
			logger.info("用户url=" + userUrl);
			String userRet = Utils.get(userUrl.toString());
			logger.info("用户Ret=" + userRet);
			JSONObject userObj = (JSONObject) JSON.parse(userRet);

			String nickname = userObj.getString("nickname");
		//	Integer sex = userObj.getInteger("sex");
			String userImg = userObj.getString("headimgurl");
			// "city":"Shenzhen","province":"Guangdong","country":"CN"
		/*	String address = "country:" + userObj.getString("country") + "," + "province:"
					+ userObj.getString("province") + "," + "city:" + userObj.getString("city");*/
			// String unionid = userObj.getString("unionid");
			userInfoService.insertWeChatInfo(openid, nickname, 3, userImg, "");

			userInfo = userInfoService.getUserInfoByUsername(openid);
			String token = this.tokenInfoService.genToken(userInfo.getUser_id());

			dataMap.put("id", userInfo.getUser_id());
			dataMap.put("username", userInfo.getUsername());
			dataMap.put("imei", userInfo.getImei());
			dataMap.put("token", token);
			dataMap.put("nickname", userInfo.getNickname());
			dataMap.put("height", userInfo.getHeight());
			dataMap.put("weight", userInfo.getWeight());
			dataMap.put("sex", userInfo.getSex());
			dataMap.put("avatar", userInfo.getAvatar());
			dataMap.put("address", userInfo.getAddress());
		}

		/*
		 * } }
		 */
		dto.setData(dataMap);
		return dto;
	}
}
