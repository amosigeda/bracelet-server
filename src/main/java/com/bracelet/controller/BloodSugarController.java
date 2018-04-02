package com.bracelet.controller;


import com.bracelet.dto.HttpBaseDto;
import com.bracelet.dto.LatestBloodSugarDto;
import com.bracelet.entity.BloodSugar;
import com.bracelet.entity.HeartRate;
import com.bracelet.service.IBloodSugarService;
import com.bracelet.service.PageParam;
import com.bracelet.service.Pagination;
import com.bracelet.util.Utils;

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
@RequestMapping("/bloodSugar")
public class BloodSugarController extends BaseController {
    @Autowired
    IBloodSugarService bloodSugarService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping("/search/latest/{token}")
    public HttpBaseDto getLatestBloodSuagr(@PathVariable String token) {
        Long user_id = checkTokenAndUser(token);
        BloodSugar bloodSugar = bloodSugarService.getLatest(user_id);
        LatestBloodSugarDto latestBloodSugarDto = null;
        if (bloodSugar != null) {
        	latestBloodSugarDto = new LatestBloodSugarDto();
        	latestBloodSugarDto.setBloodSugar(bloodSugar.getBlood_sugar());
        	latestBloodSugarDto.setTimestamp(bloodSugar.getUpload_time().getTime());
        }
        HttpBaseDto dto = new HttpBaseDto();
        dto.setData(latestBloodSugarDto);
        return dto;
    }
    
 // 血糖历史
    @ResponseBody
    @RequestMapping(value = "/history", method = RequestMethod.POST)
 	public HttpBaseDto listBloodSugarHistory(
 			@RequestParam String token,
 			@RequestParam String startTime,
 			@RequestParam String endTime) {
    	
    	Long user_id = checkTokenAndUser(token);
    	List<BloodSugar> bloodSugarList = bloodSugarService.getBloodHistory(user_id,startTime,endTime);
    	List<Map<String, Object>> dataList = new LinkedList<Map<String, Object>>();
    	if (bloodSugarList != null) {
			for (BloodSugar wlInfo : bloodSugarList) {
				Map<String, Object> dataMap = new HashMap<>();
				dataMap.put("blood_sugar", wlInfo.getBlood_sugar());
				dataMap.put("upload_time", wlInfo.getUpload_time().getTime());
				dataList.add(dataMap);
			}
		}
    	HttpBaseDto dto = new HttpBaseDto();
		dto.setData(dataList);
		return dto;
 	}
    
	// 血糖历史
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
		Pagination<BloodSugar> pagination = bloodSugarService.find(user_id, pageParam);
		List<Map<String, Object>> dataList = new LinkedList<Map<String, Object>>();
		HttpBaseDto dto = new HttpBaseDto();
		if (pagination.getResultList() != null) {
			for (BloodSugar row : pagination.getResultList()) {
				Map<String, Object> rowMap = new HashMap<>();
				rowMap.put("blood_sugar", row.getBlood_sugar());
				rowMap.put("timestamp", row.getUpload_time().getTime());
				dataList.add(rowMap);
			}
		}
		dto.setPageData(pagination, dataList);
		return dto;
	}
}
