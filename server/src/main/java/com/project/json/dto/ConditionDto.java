package com.project.json.dto;

import lombok.Data;

@Data
public class ConditionDto {
    private String field;
    private String value;
    private String predicate;

    public ConditionDto() {
    }

    public ConditionDto(String field, String value) {
        this.field = field;
        this.value = value;
    }

    public ConditionDto(String field, String value, String predicate) {
        this.field = field;
        this.value = value;
        this.predicate = predicate;
    }
}
