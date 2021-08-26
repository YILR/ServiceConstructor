package com.project.json.serviceinit.screen.component.mapservice;

import lombok.Data;

@Data
public class DictionaryFilter {

    private String attributeName;
    private String condition;
    private String value;
    private String valueType;

    public DictionaryFilter(String attributeName, String value, String valueType) {
        this.attributeName = attributeName;
        this.condition = "EQUALS";
        this.value = value;
        this.valueType = valueType;
    }
}
