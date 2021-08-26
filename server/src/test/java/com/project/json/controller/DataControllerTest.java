package com.project.json.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.json.dto.ComponentDto;
import com.project.json.dto.ScreenDto;
import com.project.json.service.DataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest
class DataControllerTest {

    @Autowired
    private DataService dataService;

    @Test
    void getScreens() {
        System.out.println(dataService.getAllScreens());
    }

    @Test
    void getComponents() {
        System.out.println(dataService.getAllComponents());
    }

    @Test
    void testFile() throws JsonProcessingException {
        ComponentDto componentDto = new ComponentDto();
        Map<String, Boolean> fileType = new LinkedHashMap<>();
        fileType.put("JPEG", false);
        fileType.put("JPG", false);
        fileType.put("PNG", false);
        fileType.put("PDF", false);
        fileType.put("RAR", false);
        fileType.put("ZIP", false);
        fileType.put("BMP", false);
        fileType.put("TIFF", false);
        fileType.put("SIG", false);
        ObjectMapper mapper = new ObjectMapper();
        componentDto.buildFileType();
        System.out.println( mapper.writeValueAsString(componentDto));
    }

    @Test
    void mapTest() throws IOException {

    }
}