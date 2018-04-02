package com.bracelet.entity;

import java.sql.Timestamp;

/**
 * 频率控制
 * 
 */
public class FrequencyContro {
    private Long id;
    private Integer type;
    private Integer frequency_time;
    private Timestamp create_time;
    private Timestamp update_time;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getFrequency_time() {
		return frequency_time;
	}
	public void setFrequency_time(Integer frequency_time) {
		this.frequency_time = frequency_time;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
    
}
