package com.bracelet.service.impl;

import com.bracelet.dto.SocketLoginDto;
import com.bracelet.entity.UserInfo;
import com.bracelet.service.IUserInfoService;
import com.bracelet.util.ChannelMap;
import com.bracelet.util.Utils;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

@Service
public class UserInfoServiceImpl implements IUserInfoService {
	@Autowired
	JdbcTemplate jdbcTemplate;
	private Logger logger = LoggerFactory.getLogger(getClass());

	public boolean insert(String tel) {
		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate.update("insert into user_info (username, createtime) values (?,?)",
				new Object[] { tel, now }, new int[] { Types.VARCHAR, Types.TIMESTAMP });
		return i == 1;
	}

	public boolean insert(String tel, String password) {
		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate.update("insert into user_info (username, password, createtime,sex) values (?,?,?,?)",
				new Object[] { tel, password, now,3 }, new int[] { Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP ,Types.INTEGER});
		return i == 1;
	}

	public boolean saveUserPassword(Long user_id, String password) {
		int i = jdbcTemplate.update("update user_info set password=? where user_id = ?",
				new Object[] { password, user_id }, new int[] { Types.VARCHAR, Types.INTEGER });
		return i == 1;
	}

	public boolean updateUserInfo(Long user_id, String avatar, String nickname, Integer sex, String weight,
			String height, String address) {
		int i = jdbcTemplate.update(
				"update user_info set avatar=?,nickname=?,sex=?,weight=?,height=?,address=? where user_id = ?",
				new Object[] { avatar, nickname, sex, weight, height, address, user_id }, new int[] { Types.VARCHAR,
						Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER });
		return i == 1;
	}

	public boolean bindDevice(Long user_id, String imei) {
		String sql = "select * from user_info where imei=? and user_id != ?";
		List<UserInfo> list = jdbcTemplate.query(sql, new Object[] { imei, user_id },
				new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
		if (list != null && !list.isEmpty()) {
			logger.warn("用户[" + user_id + "]，设备[" + imei + "]已经被绑定，请核对您的设备信息!");
			return false;
		}

		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate.update("update user_info set imei = ?, bindingtime = ? where user_id = ?",
				new Object[] { imei, now, user_id }, new int[] { Types.VARCHAR, Types.TIMESTAMP, Types.INTEGER });
		return i == 1;
	}

	public boolean unbindDevice(Long user_id, String imei) {
		boolean flag = true;
		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate.update("update user_info set imei = '', bindingtime = ? where user_id = ?",
				new Object[] { now, user_id }, new int[] { Types.TIMESTAMP, Types.INTEGER });
		flag = (i == 1);
		if (flag) {
			// clear channel
			SocketLoginDto socketLoginDto = ChannelMap.getChannel(imei);
			if (socketLoginDto == null || socketLoginDto.getChannel() == null) {
				logger.info("用户[" + user_id + "]，设备[" + imei + "]对应的channel不存在，无需关闭!");
			} else {
				Channel channel = socketLoginDto.getChannel();
				channel.close();
			}
		}
		return flag;
	}

	@Override
	public UserInfo getUserInfoByImei(String imei) {
		String sql = "select * from user_info where imei=? LIMIT 1";
		List<UserInfo> list = jdbcTemplate.query(sql, new Object[] { imei },
				new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			logger.info("cannot find userinfo,imei:" + imei);
		}
		return null;
	}

	@Override
	public UserInfo getUserInfoById(Long id) {
		String sql = "select * from user_info where user_id=? LIMIT 1";
		List<UserInfo> list = jdbcTemplate.query(sql, new Object[] { id },
				new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			logger.info("cannot find userinfo,id:" + id);
		}
		return null;
	}

	@Override
	public UserInfo getUserInfoByUsername(String username) {
		String sql = "select * from user_info where username=? LIMIT 1";
		List<UserInfo> list = jdbcTemplate.query(sql, new Object[] { username },
				new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			logger.info("cannot find userinfo,username:" + username);
		}
		return null;
	}

	@Override
	public boolean updateImeiStatus(Long user_id, Integer i) {
		int ii = jdbcTemplate.update("update user_info set status=?  where user_id = ?", new Object[] { i, user_id },
				new int[] { Types.INTEGER, Types.INTEGER });
		return ii == 1;
	}

	@Override
	public boolean insertWeChatInfo(String openid, String nickname, Integer sex, String userImg,String address) {
		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate.update(
				"insert into user_info (username, password,nickname,sex, head, address,createtime) values (?,?,?,?,?,?,?)",
				new Object[] { openid, "E10ADC3949BA59ABBE56E057F20F883E", nickname, sex, userImg,address, now },
				new int[] { Types.VARCHAR, Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.VARCHAR, Types.TIMESTAMP });
		return i == 1;
	}
}
