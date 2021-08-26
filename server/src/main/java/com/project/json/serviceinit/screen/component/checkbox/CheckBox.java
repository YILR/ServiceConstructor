package com.project.json.serviceinit.screen.component.checkbox;

import com.project.json.serviceinit.screen.component.AbstractComponent;
import lombok.Data;

@Data
public class CheckBox extends AbstractComponent {

    private AttrsBox attrs = new AttrsBox();
    private Boolean required = false;
    private Boolean value = false;
    private Boolean visited = false;

    public CheckBox(String id, String label) {
        this.id = id;
        this.type = "CheckBox";
        this.label = label;
    }
}
