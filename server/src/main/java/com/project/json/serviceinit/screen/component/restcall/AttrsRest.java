package com.project.json.serviceinit.screen.component.restcall;

import lombok.Data;

@Data
public class AttrsRest {

    private String url = "https://pgu-dev-fed.test.gosuslugi.ru";
    private String method = "GET";
    private String path = "/api/nsi/v1/epgu/region/${region}/service/600";
    private String body = "";
    private SimpleObj queryParameters = new SimpleObj();
    private SimpleObj headers = new SimpleObj();
    private SimpleObj cookies = new SimpleObj();
    private Ref refs = new Ref();

    public AttrsRest(int code) {
        path+= code;
    }
}
