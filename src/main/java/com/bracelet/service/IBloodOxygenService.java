package com.bracelet.service;

import java.sql.Timestamp;
import java.util.List;

import com.bracelet.entity.BloodOxygen;


public interface IBloodOxygenService {
    boolean insert(Long user_id, Integer pulseRate,Integer bloodOxygen, Timestamp timsTimestamp);

	BloodOxygen getLatest(Long user_id);

	List<BloodOxygen> getOxygenHistory(Long user_id, String startTime, String endTime);

	Pagination<BloodOxygen> find(Long user_id, PageParam pageParam);
}
