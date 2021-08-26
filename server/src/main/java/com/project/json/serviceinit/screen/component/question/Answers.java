package com.project.json.serviceinit.screen.component.question;

import lombok.Data;

@Data
public class Answers {


    private String label;
    private String value;
    private String type = "nextStep";
    private String action = "getNextScreen";

    public Answers(String label, String value) {
        this.label = label;
        this.value = value;
    }
}
