package com.project.json.serviceinit.screen.component.childrenlist;

import lombok.Data;

import java.util.List;

@Data
public class AttrsChild {

    private Boolean singleChild;
    private String repeatAmount;
    private Boolean isCycled;
    private Integer minAge;
    private Integer maxAge;
    private List<String> components;

    public AttrsChild(Boolean singleChild, Boolean isCycled, Integer minAge, Integer maxAge, List<String> components) {
        this.singleChild = singleChild;
        if(singleChild != null && singleChild){
            repeatAmount = "1";
        }
        this.isCycled = isCycled;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.components = components;
    }
}
