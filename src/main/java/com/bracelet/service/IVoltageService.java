package com.bracelet.service;

import java.sql.Timestamp;

import com.bracelet.entity.Voltage;


public interface IVoltageService {
    boolean insert(Long user_id, String imei,Integer voltage, Timestamp timsTimestamp);

	Voltage getLatest(Long user_id);

}
