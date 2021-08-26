package com.project.json.serviceinit.screen.component.stringinput;

import lombok.Data;

@Data
public class Validation {

    private String type = "RegExp";
    private String value;
    private String ref = "";
    private String dataType = "";
    private String condition = "";
    private String errorMsg;

    public Validation(String value, String errorMsg) {
        this.value = value;
        this.errorMsg = errorMsg;
    }
}
