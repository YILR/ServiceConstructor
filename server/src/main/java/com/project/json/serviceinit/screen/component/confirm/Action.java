package com.project.json.serviceinit.screen.component.confirm;

import lombok.Data;

@Data
public class Action {

    private String label;
    private String value = "";
    private String type;
    private String action;

    public Action(String label, String type, String action) {
        this.label = label;
        this.type = type;
        this.action = action;
    }
}
