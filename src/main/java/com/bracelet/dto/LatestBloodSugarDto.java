package com.bracelet.dto;

/**
 * 最近血糖
 * 
 */
public class LatestBloodSugarDto {
	
    private String bloodSugar;
    private Long timestamp;
	
	public String getBloodSugar() {
		return bloodSugar;
	}
	public void setBloodSugar(String bloodSugar) {
		this.bloodSugar = bloodSugar;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

}
