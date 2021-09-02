package com.project.json.controller;

import com.project.json.service.DataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


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

}