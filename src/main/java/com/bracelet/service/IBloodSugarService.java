package com.bracelet.service;

import java.sql.Timestamp;
import java.util.List;

import com.bracelet.entity.BloodSugar;


public interface IBloodSugarService {
    boolean insert(Long user_id, String bloodSugar, Timestamp timsTimestamp);

	BloodSugar getLatest(Long user_id);

	List<BloodSugar> getBloodHistory(Long user_id, String startTime, String endTime);

	Pagination<BloodSugar> find(Long user_id, PageParam pageParam);

	
}
