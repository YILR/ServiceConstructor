package com.project.json.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;

@SpringBootTest
@AutoConfigureMockMvc
public class ServiceInitTest {

    @Autowired
    JsonController jsonController;
    @Autowired
    MockMvc mockMvc;

    @Test
    void serviceInitTest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(new File("src\\test\\java\\com\\project\\json\\controller\\resources\\serviceinit.json"));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/create/246")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.readTree(new File("src\\test\\java\\com\\project\\json\\controller\\resources\\main.json")).toString())
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        String json = mvcResult.getResponse().getContentAsString();

        JsonNode jsonNode1 = mapper.readTree(json);

        mapper.writeValue(new File("src\\test\\java\\com\\project\\json\\controller\\resources\\serviceinit2.json"), jsonNode1);

        assertEquals(jsonNode, jsonNode1);
    }

}
