package com.project.json.service;

import com.project.json.dto.ScreenDto;
import com.project.json.serviceinit.ServiceInit;
import com.project.json.util.ApplicantUtil;
import com.project.json.util.ScreenUtil;
import com.project.json.util.ServiceUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class JsonService {

    public ServiceInit createService(List<ScreenDto> screenDtos, int code){
        ServiceInit serviceInit = new ServiceInit(code);
        ApplicantUtil.initPersons(serviceInit, screenDtos);
        ServiceUtil.initService(serviceInit, screenDtos);
        serviceInit.initEnd(code);
        return serviceInit;
    }

    public List<ScreenDto> postCheckScreen(List<ScreenDto> screenDto) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        return    mapper.readValue(new File("src\\test\\java\\com\\project\\json\\controller\\resources\\ass.json"), List.class);
        return ScreenUtil.checkScreen(screenDto);
    }
}
