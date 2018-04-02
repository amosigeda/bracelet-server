package com.bracelet.service;

import java.util.List;

import com.bracelet.entity.Fence;
import com.bracelet.entity.FenceStatus;
import com.bracelet.entity.OddShape;

public interface IFenceService {

	Fence getOne(Long user_id);
	FenceStatus getFenceStatus(Long user_id);
	

	boolean insert(Long user_id, String lat, String lng, Integer radius,String name);
	boolean insertFenceStaus(Long user_id);
	
	boolean insertOddShape(Long user_id, String point,String name,String type);

	boolean update(Long id, Long user_id, String lat, String lng, Integer radius,String name);

	boolean delete(Long id, Long user_id);

	void checkFenceArea(Long user_id, String imei, String lat, String lng, long time);

	boolean updateStatus(Long id, Integer status);
	boolean updateFenceStatus(Long id, Integer status);
	
	OddShape getOddShape(Long user_id);

	boolean deleteOddShape(Long id, Long user_id);

	boolean updateOddShapefence(Long id, Long user_id, String point,String name);

	List<OddShape> getOddShapeList(Long user_id);
	List<Fence> getRoundFenceList(Long user_id);

}
