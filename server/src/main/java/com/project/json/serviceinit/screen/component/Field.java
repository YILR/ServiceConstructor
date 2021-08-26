package com.project.json.serviceinit.screen.component;

import lombok.Data;

@Data
public class Field {

    private String fieldName;
    private String label;

    public Field(String fieldName, String label) {
        this.fieldName = fieldName;
        this.label = label;
    }
}
