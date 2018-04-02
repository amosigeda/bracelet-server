package com.bracelet.dto;

import com.alibaba.fastjson.JSON;
//电子围栏
public class FenceDto {
	private Long timestamp;
	private String lat;
	private String lng;
	private String content;
	private Integer locationType;
	

	public Integer getLocationType() {
		return locationType;
	}

	public void setLocationType(Integer locationType) {
		this.locationType = locationType;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public static void main(String[] args) {
		FenceDto sosDto = new FenceDto();
		sosDto.setLat("22.33123");
		sosDto.setLng("123.123123");
		sosDto.setTimestamp(System.currentTimeMillis());
		System.out.println(JSON.toJSONString(sosDto));
	}
}
