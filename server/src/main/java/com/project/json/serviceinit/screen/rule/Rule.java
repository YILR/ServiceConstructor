package com.project.json.serviceinit.screen.rule;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class Rule {


    protected List<Condition> conditions = new ArrayList<>();
    protected String nextDisplay;


    public void protectedField(String predicate, String value){
        conditions.add(new Condition(null,"userRole", predicate, value));
    }

    public void field(String field, String value, String predicate){
        conditions.add(new Condition(field, null,  predicate, value));
    }
}
