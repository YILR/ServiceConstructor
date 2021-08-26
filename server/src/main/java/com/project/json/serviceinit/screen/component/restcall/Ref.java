package com.project.json.serviceinit.screen.component.restcall;

import lombok.Data;

@Data
public class Ref {

    private String region = "userRegion.codes[0]";

    public Ref() {
    }

    public Ref(String region) {
        this.region = region;
    }
}
