package com.bracelet.service;

import java.sql.Timestamp;
import java.util.List;

import com.bracelet.entity.FrequencyContro;
import com.bracelet.entity.Location;
import com.bracelet.entity.LocationModel;
import com.bracelet.entity.LocationPointc;

public interface ILocationService {

	Location getLatest(Long user_id);

	List<Location> getFootprint(Long user_id, String type,String showType);

	boolean insert(Long user_id, String imei,Integer type,String location_type, String lat, String lng, Integer accuracy, Integer status,
			Timestamp timestamp,Integer showType,String address);

	Location getRealtimeLocation(Long user_id, Integer status);

	FrequencyContro getLocationCheckFrequency(Integer type);

	Location getShowLatest(Long user_id);

	boolean insert(Long user_id, Integer type);

	LocationPointc getLatestC(Long user_id);

	boolean deleteLatestC(Long id, Long user_id);

	List<Location> getFootPointPrint(Long user_id, Integer locationType, Integer pointType, String startTime, String endTime);

	boolean insertLocationModel(Long user_id, String imei,Integer type);

	LocationModel getLastModel(Long user_id);

	boolean updateLocationModel(Long id, Integer type);
}
