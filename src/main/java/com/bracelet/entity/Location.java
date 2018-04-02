package com.bracelet.entity;

import java.sql.Timestamp;

/**
 * 位置
 * 
 */
public class Location {

	private Long l_id;
	private Long user_id;
	private String imei;
	private String location_type;
	private String lat;
	private String lng;
	private Integer accuracy;
	
	// 暂时不用
	private Integer status;
	private Timestamp upload_time;
	private Integer push_type;
	private Integer show_type;
	
	private String address;
	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getL_id() {
		return l_id;
	}

	public void setL_id(Long l_id) {
		this.l_id = l_id;
	}

	public String getLocation_type() {
		return location_type;
	}

	public void setLocation_type(String location_type) {
		this.location_type = location_type;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getUpload_time() {
		return upload_time;
	}

	public void setUpload_time(Timestamp upload_time) {
		this.upload_time = upload_time;
	}

	/**
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}

	/**
	 * @param imei the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}

	public Integer getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(Integer accuracy) {
		this.accuracy = accuracy;
	}

	public Integer getPush_type() {
		return push_type;
	}

	public void setPush_type(Integer push_type) {
		this.push_type = push_type;
	}

	public Integer getShow_type() {
		return show_type;
	}

	public void setShow_type(Integer show_type) {
		this.show_type = show_type;
	}
}
