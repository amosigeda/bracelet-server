package com.bracelet.service.impl;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bracelet.entity.FrequencyContro;
import com.bracelet.entity.Location;
import com.bracelet.entity.LocationModel;
import com.bracelet.entity.LocationPointc;
import com.bracelet.service.ILocationService;
import com.bracelet.util.Utils;

@Service
public class LocationServiceImpl implements ILocationService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Location> getFootprint(Long user_id, String type, String showType) {
		Calendar calendar = Calendar.getInstance();
		if("3".equals(type)){
			calendar.add(Calendar.DATE, -0);
		}else if ("2".equals(type)) {
			// 过去一天
			calendar.add(Calendar.DAY_OF_YEAR, -1);
		} else {
			// 过去1小时
			calendar.add(Calendar.HOUR_OF_DAY, -1);
		}
		String dateString = Utils.format14DateString(calendar.getTime());
		String sql = "select * from location where user_id=?  and upload_time >= ?  and  upload_time <= ? and show_type= ?   order by upload_time asc";
		List<Location> list = jdbcTemplate.query(sql, new Object[] { user_id,Utils.formatDateDay(new Date()), dateString, showType },
				new BeanPropertyRowMapper<Location>(Location.class));
		return list;
	}

	public Location getLatest(Long user_id) {
		String sql = "select * from location where user_id=? order by upload_time desc LIMIT 1";
		List<Location> list = jdbcTemplate.query(sql, new Object[] { user_id },
				new BeanPropertyRowMapper<Location>(Location.class));

		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			logger.info("getLatest return null.user_id:" + user_id);
		}
		return null;
	}

	public Location getRealtimeLocation(Long user_id, Integer status) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, -15);
		String dateString = Utils.format14DateString(calendar.getTime());
		String sql = "select * from location where user_id=? and status= ? and upload_time > ? order by upload_time desc LIMIT 1";
		List<Location> list = jdbcTemplate.query(sql, new Object[] { user_id, status, dateString },
				new BeanPropertyRowMapper<Location>(Location.class));

		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			logger.info("getLatest return null.user_id:" + user_id);
		}
		return null;
	}

	public boolean insert(Long user_id, String imei, Integer Type, String location_type, String lat, String lng,
			Integer accuracy, Integer status, Timestamp timestamp, Integer showType,String address) {
		int i = jdbcTemplate.update(
				"insert into location (user_id, imei,push_type,location_type, lat, lng, accuracy, status, upload_time,show_type,address) values (?,?,?,?,?,?,?,?,?,?,?)",
				new Object[] { user_id, imei, Type, location_type, lat, lng, accuracy, status, timestamp, showType,address },
				new int[] { java.sql.Types.INTEGER, java.sql.Types.VARCHAR, java.sql.Types.INTEGER,
						java.sql.Types.VARCHAR, java.sql.Types.VARCHAR, java.sql.Types.VARCHAR, java.sql.Types.INTEGER,
						java.sql.Types.INTEGER, java.sql.Types.TIMESTAMP, java.sql.Types.INTEGER, java.sql.Types.VARCHAR });
		return i == 1;
	}

	@Override
	public FrequencyContro getLocationCheckFrequency(Integer type) {
		String sql = "select *  from  location_frequency_contorller  where type=? order by id desc LIMIT 1";
		List<FrequencyContro> list = jdbcTemplate.query(sql, new Object[] { type },
				new BeanPropertyRowMapper<FrequencyContro>(FrequencyContro.class));
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			logger.info("getLatest return null.user_id:" + type);
		}
		return null;
	}

	@Override
	public Location getShowLatest(Long user_id) {
		String sql = "select * from location where user_id=? and show_type=1 order by upload_time desc LIMIT 1";
		List<Location> list = jdbcTemplate.query(sql, new Object[] { user_id },
				new BeanPropertyRowMapper<Location>(Location.class));

		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			logger.info("getLatest return null.user_id:" + user_id);
		}
		return null;
	}

	@Override
	public boolean insert(Long user_id, Integer type) {
		Timestamp now = Utils.getCurrentTimestamp();

		int i = jdbcTemplate.update("insert into control_guiji (user_id,type, createtime) values (?,?,?)",
				new Object[] { user_id, type, now }, new int[] { Types.INTEGER, Types.INTEGER, Types.TIMESTAMP });
		return i == 1;
	}

	@Override
	public LocationPointc getLatestC(Long user_id) {
		String sql = "select * from control_guiji where user_id=? order by id desc LIMIT 1";
		List<LocationPointc> list = jdbcTemplate.query(sql, new Object[] { user_id },
				new BeanPropertyRowMapper<LocationPointc>(LocationPointc.class));

		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			logger.info("LocationPointc return null.user_id:" + user_id);
		}
		return null;
	}

	@Override
	public boolean deleteLatestC(Long id, Long user_id) {
		int i = jdbcTemplate.update("delete from control_guiji where id = ? and user_id = ?", new Object[] { id, user_id },
				new int[] { Types.INTEGER, Types.INTEGER });
		return i == 1;
	}

	@Override
	public List<Location> getFootPointPrint(Long user_id, 
			Integer locationType, Integer pointType, String startTime,
			String endTime) {
		String condition="and location_type='"+locationType+"'  "
				+ "  and upload_time >= '"+startTime+"' and upload_time <='"+endTime+"' and show_type="+  pointType +"order by id asc";
		String sql = "select * from location where user_id=? "+condition;
		logger.info("拼轨迹点查询sql语句="+sql);
		List<Location> list = jdbcTemplate.query(sql, new Object[] { user_id },
				new BeanPropertyRowMapper<Location>(Location.class));
		return list;
	}

	@Override
	public boolean insertLocationModel(Long user_id, String imei,Integer type) {
		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate.update(
				"insert into location_model (user_id, imei,type,create_time,update_time) values (?,?,?,?,?)",
				new Object[] { user_id, imei, type,now,now },
				new int[] { java.sql.Types.INTEGER, java.sql.Types.VARCHAR, java.sql.Types.INTEGER, java.sql.Types.TIMESTAMP, java.sql.Types.TIMESTAMP});
		return i == 1;
	}

	@Override
	public LocationModel getLastModel(Long user_id) {
		String sql = "select * from location_model where user_id=?  LIMIT 1";
		List<LocationModel> list = jdbcTemplate.query(sql, new Object[] { user_id },
				new BeanPropertyRowMapper<LocationModel>(LocationModel.class));

		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			logger.info("getLatest return null.user_id:" + user_id);
		}
		return null;
	}

	@Override
	public boolean updateLocationModel(Long id, Integer type) {
		Timestamp now = Utils.getCurrentTimestamp();
		int i = jdbcTemplate.update("update location_model set type=?,update_time=?  where id = ?",
				new Object[] { type,now, id }, 
				new int[] {  Types.INTEGER, Types.TIMESTAMP, Types.INTEGER });
		return i == 1;
	}
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -0);
		String dateString =Utils.format14DateString(calendar.getTime());
		//String dateString = Utils.formatDateDay(new Date());
		System.out.println(dateString);
	}

}
