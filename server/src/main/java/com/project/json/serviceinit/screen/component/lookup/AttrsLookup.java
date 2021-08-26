package com.project.json.serviceinit.screen.component.lookup;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
public class AttrsLookup {

    private String dictionaryType;
    private List<String> ref = new ArrayList<>();
    private String defaultIndex = "0";
    private Boolean required = true;

    public AttrsLookup(String dictionaryType) {
        this.dictionaryType = dictionaryType;
    }
}
