package com.project.json.serviceinit.screen.component.checkbox;

import lombok.Data;

@Data
public class Validation {

    private String condition = "atLeastOne";
    private String errorMsg = "Необходимо выбрать хотя бы один вариант";
}
