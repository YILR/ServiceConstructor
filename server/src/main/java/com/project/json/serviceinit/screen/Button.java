package com.project.json.serviceinit.screen;

import lombok.Data;

@Data
public class Button {
    
    private String label= "";
    private String type= "nextStep";
    private String action= "getNextScreen";

    public Button(String label) {
        this.label = label;
    }
}
