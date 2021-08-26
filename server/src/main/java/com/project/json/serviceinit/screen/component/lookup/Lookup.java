package com.project.json.serviceinit.screen.component.lookup;

import com.project.json.serviceinit.screen.component.AbstractComponent;
import lombok.Data;

@Data
public class Lookup extends AbstractComponent {

    private AttrsLookup attrs;
    private String value = "";
    private Boolean visited = false;


    public Lookup(String id, String label, String dictionaryType) {
        this.id = id;
        this.type = "Lookup";
        this.label = label;
        attrs = new AttrsLookup(dictionaryType);
    }
}
