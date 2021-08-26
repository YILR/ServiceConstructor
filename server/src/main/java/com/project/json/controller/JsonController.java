package com.project.json.controller;

import com.project.json.dto.ScreenDto;
import com.project.json.service.JsonService;
import com.project.json.serviceinit.ServiceInit;
import com.project.json.util.roleapplicant.BusinessUtil;
import com.project.json.util.roleapplicant.LegalUtil;
import com.project.json.util.roleapplicant.PersonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class JsonController {

    @Autowired
   private JsonService jsonService;

    @PostMapping("/create/{code}")
    public ServiceInit createService(@RequestBody List<ScreenDto> screenDto, @PathVariable int code){
        System.out.println(screenDto);
        return jsonService.createService(screenDto, code);
    }

    @PostMapping("/check")
    public ResponseEntity<List<ScreenDto>> postCheckScreens(@RequestBody List<ScreenDto> screenDto) throws IOException {
        return ResponseEntity.ok(jsonService.postCheckScreen(screenDto));
    }

    @PostMapping("/oncheck")
    public ResponseEntity<Collection<ScreenDto>> postOnCheckScreens(@RequestBody List<ScreenDto> screenDtos){
        Map<String, ScreenDto> map = screenDtos.stream()
                .collect(Collectors.toMap(ScreenDto::getId, Function.identity(), (a, b) -> b, LinkedHashMap::new));
        return ResponseEntity.ok(map.values());
    }

    @PostMapping("/test")
    public ServiceInit getTest(@RequestBody ScreenDto screenDto){
        ServiceInit serviceInit = new ServiceInit(246);
        serviceInit.initMain(screenDto);
        System.out.println(screenDto);
        PersonUtil.initFl(serviceInit,null);
        BusinessUtil.initIp(serviceInit, null);
        LegalUtil.initUl(serviceInit, new ArrayList<>());
        PersonUtil.representativeFl(serviceInit,null);
        BusinessUtil.representativeIp(serviceInit,null);
        LegalUtil.representativeUl(serviceInit,null);
        PersonUtil.applicantFl(serviceInit,null);
        BusinessUtil.applicantIp(serviceInit,null);
        LegalUtil.applicantUl(serviceInit,null);
        serviceInit.initEnd(246);
        return serviceInit;
    }


}
