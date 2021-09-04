package com.project.json.serviceinit.screen.component.lookup;

import com.project.json.serviceinit.screen.component.AbstractComponent;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Lookup extends AbstractComponent {

    private AttrsLookup attrs;
    private String value = "";
    private Boolean visited = false;


    public Lookup(String id, String type, String label, String dictionaryType) {
        super(id, type, label);
        attrs = new AttrsLookup(dictionaryType);
    }
}
