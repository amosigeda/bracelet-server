package com.bracelet.service;

import com.bracelet.entity.UserInfo;

public interface IUserInfoService {

	boolean insert(String tel);

	boolean insert(String tel, String password);

	boolean saveUserPassword(Long user_id, String password);

	boolean updateUserInfo(Long user_id, String avatar, String nickname, Integer sex, String weight, String height, String address);

	boolean bindDevice(Long user_id, String imei);

	boolean unbindDevice(Long user_id, String imei);

	UserInfo getUserInfoByImei(String imei);

	UserInfo getUserInfoById(Long id);

	UserInfo getUserInfoByUsername(String username);

	boolean updateImeiStatus(Long user_id, Integer i);

	boolean insertWeChatInfo(String openid, String nickname, Integer sex, String userImg,String address);

}
