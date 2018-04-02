package com.bracelet.entity;

import java.sql.Timestamp;

/**
 * 敏感区域
 * 
 */
public class SensitiveArea {

	private Long id;
	private Long user_id;
	private String name;
	private String lat;
	private String lng;
	private Integer radius;
	// 0: 未知，1:电子围栏内，2:电子围栏外
		private Integer status;
	
	private Timestamp  uploadtime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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

	public Integer getRadius() {
		return radius;
	}

	public void setRadius(Integer radius) {
		this.radius = radius;
	}

	public Timestamp getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(Timestamp uploadtime) {
		this.uploadtime = uploadtime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
