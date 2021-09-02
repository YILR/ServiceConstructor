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

    @PostMapping("/checkApplicant")
    public ResponseEntity<List<ScreenDto>> postCheckApplicantScreens(@RequestBody List<ScreenDto> screenDto) throws IOException {
        return ResponseEntity.ok(jsonService.postCheckScreen(screenDto));
    }

    @PostMapping("/checkCopy")
    public ResponseEntity<Collection<ScreenDto>> postOnCheckScreens(@RequestBody List<ScreenDto> screenDtos){
        Map<String, ScreenDto> map = screenDtos.stream()
                .collect(Collectors.toMap(ScreenDto::getId, Function.identity(), (a, b) -> b, LinkedHashMap::new));
        return ResponseEntity.ok(map.values());
    }

}
