package com.project.json.serviceinit.screen.component.otherinput;

import lombok.Data;

@Data
public class ValidOther {

    private String type;
    private String errorMsg;

    public ValidOther(String type, String errorMsg) {
        this.type = type;
        this.errorMsg = errorMsg;
    }
}
