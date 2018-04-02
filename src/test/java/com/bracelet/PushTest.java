package com.bracelet;

import com.alibaba.fastjson.JSON;
import com.bracelet.dto.SosDto;
import com.bracelet.util.PushUtil;

public class PushTest {
    public static void main(String[] args) {
        // PushRequest pushRequest = new PushRequest();
        // pushRequest.setAppKey(24620906L);
        // pushRequest.setTargetValue("");
        SosDto dto = new SosDto();
        dto.setLat("22222222222222222");
        dto.setLng("138888813838");
        dto.setTimestamp(System.currentTimeMillis());
        System.out.println(JSON.toJSONString(dto));

//        PushUtil.push("7365E1C5409AC8930471A641C1A03924", "66666666666", JSON.toJSONString(dto), "测试");
        PushUtil.push("12AF8FA10FCC8A8F888D95CD0FCC8775", "dfafkdshfa", JSON.toJSONString(dto), JSON.toJSONString(dto));
    }
    
}
