package com.bracelet.controller;

import com.bracelet.dto.HttpBaseDto;
import com.bracelet.dto.LatestBloodOxygenDto;
import com.bracelet.entity.BloodOxygen;
import com.bracelet.entity.BloodSugar;
import com.bracelet.service.IBloodOxygenService;
import com.bracelet.service.PageParam;
import com.bracelet.service.Pagination;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/bloodOxygen")
public class BloodOxygenController extends BaseController {
    @Autowired
    IBloodOxygenService bloodOxygenService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping("/search/latest/{token}")
    public HttpBaseDto getLatestBloodOxygen(@PathVariable String token) {
        Long user_id = checkTokenAndUser(token);
        BloodOxygen bloodOxygen= bloodOxygenService.getLatest(user_id);
        LatestBloodOxygenDto latestBloodOxygenDto = null;
        if (bloodOxygen != null) {
        	latestBloodOxygenDto = new LatestBloodOxygenDto();
        	latestBloodOxygenDto.setBloodOxygen(bloodOxygen.getBlood_oxygen());
        	latestBloodOxygenDto.setPulseRate(bloodOxygen.getPulse_rate());
        	latestBloodOxygenDto.setTimestamp(bloodOxygen.getUpload_time().getTime());
        }
        HttpBaseDto dto = new HttpBaseDto();
        dto.setData(latestBloodOxygenDto);
        return dto;
    }
    
 // 血氧历史
    @ResponseBody
    @RequestMapping(value = "/history", method = RequestMethod.POST)
 	public HttpBaseDto listBloodOxygenHistory(
 			@RequestParam String token,
 			@RequestParam String startTime,
 			@RequestParam String endTime) {
    	
    	Long user_id = checkTokenAndUser(token);
    	List<BloodOxygen> bloodOxygenList = bloodOxygenService.getOxygenHistory(user_id,startTime,endTime);
    	List<Map<String, Object>> dataList = new LinkedList<Map<String, Object>>();
    	if (bloodOxygenList != null) {
			for (BloodOxygen wlInfo : bloodOxygenList) {
				Map<String, Object> dataMap = new HashMap<>();
				dataMap.put("pulse_rate", wlInfo.getPulse_rate());
				dataMap.put("upload_time", wlInfo.getUpload_time());
				dataMap.put("blood_oxygen", wlInfo.getBlood_oxygen());
				dataList.add(dataMap);
			}
		}
    	HttpBaseDto dto = new HttpBaseDto();
		dto.setData(dataList);
		return dto;
 	}
    
	// 血氧历史
	@ResponseBody
	@RequestMapping(value = "/history/{token}", method = RequestMethod.GET)
	public HttpBaseDto listHeartRate(@PathVariable String token,
			@RequestParam(value = "page", required = false) Integer page) {
		Long user_id = checkTokenAndUser(token);
		logger.info("[heartRate] [history] page:" + page);
		PageParam pageParam = new PageParam();
		if (page != null && page > 0) {
			pageParam.setPage(page.intValue());
		}
		pageParam.setSort("upload_time");
		Pagination<BloodOxygen> pagination = bloodOxygenService.find(user_id, pageParam);
		List<Map<String, Object>> dataList = new LinkedList<Map<String, Object>>();
		HttpBaseDto dto = new HttpBaseDto();
		if (pagination.getResultList() != null) {
			for (BloodOxygen row : pagination.getResultList()) {
				Map<String, Object> rowMap = new HashMap<>();
				rowMap.put("pulse_rate", row.getPulse_rate());
				rowMap.put("blood_oxygen", row.getBlood_oxygen());
				rowMap.put("timestamp", row.getUpload_time().getTime());
				dataList.add(rowMap);
			}
		}
		dto.setPageData(pagination, dataList);
		return dto;
	}
}
