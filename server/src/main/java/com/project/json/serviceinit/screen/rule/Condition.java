package com.project.json.serviceinit.screen.rule;

import lombok.Data;

import java.util.*;

@Data
public class Condition {

    private String field;
    private String protectedField;
    private Boolean visited = true;
    private String value;
    private String fieldType;
    private String predicate;
    private List<Map<String, String>> args;

    public Condition(String field) {
        this.field = field;
    }

    public Condition(String field, String value) {
        this.field = field;
        this.value = Objects.equals(value, "") ? null : value;
    }


    public Condition(String field, String value, String predicate) {
        this.fieldType = "String";
        this.predicate = predicate;
        fieldOrProtected(field);
        args = new ArrayList<>();
        args.add(new LinkedHashMap<>());
        args.get(0).put("type", "UserConst");
        args.get(0).put("value", value);
        predicateIsnull();
    }

    public Condition(String field, String protectedField, String predicate, String value) {
        this.field = field;
        this.protectedField = protectedField;
        this.fieldType = "String";
        this.predicate = predicate;
        visited = null;
        args = new ArrayList<>();
        args.add(new LinkedHashMap<>());
        args.get(0).put("type", "UserConst");
        args.get(0).put("value", value);
    }

    private void fieldOrProtected(String field) {
        if (field.equals("orgType")) {
            this.protectedField = field;
            visited = true;
        } else {
            this.field = field;
            visited = null;
        }
    }

    private void predicateIsnull(){
        if (predicate.isEmpty()) {
            predicate = "isNull";
            args = new ArrayList<>();
        }
    }
}
