package com.bracelet.service.impl;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.bracelet.entity.BloodSugar;
import com.bracelet.entity.HeartPressure;
import com.bracelet.entity.WhiteListInfo;
import com.bracelet.service.IBloodSugarService;
import com.bracelet.service.PageParam;
import com.bracelet.service.Pagination;

/**
 * 血糖服务
 */
@Service
public class BloodSugarServiceImpl implements IBloodSugarService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	    @Autowired
	    JdbcTemplate jdbcTemplate;
		@Override
		public boolean insert(Long user_id, String bloodSugar,
				Timestamp timsTimestamp) {
			 int i = jdbcTemplate
		                .update("insert into bloodsugar_info (user_id, blood_sugar,upload_time) values (?,?,?)",
		                        new Object[] { user_id, bloodSugar, timsTimestamp }, new int[] {
		                                Types.INTEGER, Types.VARCHAR, 
		                                Types.TIMESTAMP });
		        return i == 1;
		}
		@Override
		public BloodSugar getLatest(Long user_id) {
	        String sql = "select * from bloodsugar_info where user_id=? order by upload_time desc LIMIT 1";
	        List<BloodSugar> list = jdbcTemplate.query(sql, new Object[] { user_id }, new BeanPropertyRowMapper<BloodSugar>(BloodSugar.class));
	        if (list != null && !list.isEmpty()) {
	            return list.get(0);
	        } else {
	            logger.info("getLatest reutrn null.user_id:" + user_id);
	        }

	        return null;
	    }
		@Override
		public List<BloodSugar> getBloodHistory(Long user_id, String startTime, String endTime) {
            String condition="  and upload_time>='"+startTime+"'  and upload_time<='"+endTime+"'"; 
			String sql = "select * from bloodsugar_info where user_id=?"+condition+" order by upload_time desc ";
			//logger.info("查询历史血糖sql="+sql);
			List<BloodSugar> list = jdbcTemplate.query(sql, new Object[] { user_id},
					new BeanPropertyRowMapper<BloodSugar>(BloodSugar.class));
			if (list != null && !list.isEmpty()) {
				return list;
			} else {
				logger.info("查询血糖结果为空:" + user_id);
			}
			return null;
		
		}
		@Override
		public Pagination<BloodSugar> find(Long user_id, PageParam pageParam) {
			String sql = "select * from bloodsugar_info where user_id=?";
			return new Pagination<BloodSugar>(sql, new Object[] { user_id }, pageParam, jdbcTemplate, BloodSugar.class);
		}
		
	
	
}
