package com.project.json.serviceinit.screen.component.repeatable;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AttrsRep {

    private Boolean isCycled = false;
    private List<String> components;

    public AttrsRep(List<String> components) {
        this.components = components;
    }
}
