package com.project.json.serviceinit.screen.component.redirect;

import lombok.Data;

@Data
public class ActionRed {

    private String label = "";
    private String value = "";
    private String type = "nextStep";
    private String action = "getNextScreen";

    public ActionRed() {
    }

    public ActionRed(String label, String value) {
        this.label = label;
        this.value = value;
        type = "redirectToLK";
        action = "getNextScreen";
    }
}
