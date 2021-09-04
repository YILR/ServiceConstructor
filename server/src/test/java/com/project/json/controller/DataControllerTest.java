package com.project.json.controller;

import com.project.json.service.DataService;
import com.project.json.serviceinit.ServiceInit;
import com.project.json.serviceinit.screen.component.labelsection.LabelSection;
import com.project.json.serviceinit.screen.component.textarea.TextArea;
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

    @Test
    void testServiceInit(){
        ServiceInit serviceInit = new ServiceInit(246);
        System.out.println( serviceInit.getApplicationFields().add(new LabelSection("FirstName", "FirstName", "FirstName")));
        System.out.println(serviceInit.getApplicationFields().size());
        System.out.println(serviceInit.getApplicationFields().add(new LabelSection("LastName", "FirstName", "FirstName")));
        System.out.println(serviceInit.getApplicationFields().size());
        System.out.println(serviceInit.getApplicationFields());
    }

}