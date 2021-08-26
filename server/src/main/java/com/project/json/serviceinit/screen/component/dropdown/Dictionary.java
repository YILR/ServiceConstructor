package com.project.json.serviceinit.screen.component.dropdown;

import lombok.Data;

@Data
public class Dictionary {
    private String label;
    private Boolean disable = false;
    private String code;

    public Dictionary(String label, String code) {
        this.label = label;
        this.code = code;
    }
}
