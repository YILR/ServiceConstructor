package com.project.json.serviceinit.screen.component.mapservice;

import lombok.Data;

@Data
public class BaloonContent {

    private String name;
    private String label;

    public BaloonContent(String name, String label) {
        this.name = name;
        this.label = label;
    }
}
