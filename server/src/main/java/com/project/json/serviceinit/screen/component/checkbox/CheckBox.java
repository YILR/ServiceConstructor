package com.project.json.serviceinit.screen.component.checkbox;

import com.project.json.serviceinit.screen.component.AbstractComponent;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CheckBox extends AbstractComponent {

    private AttrsBox attrs = new AttrsBox();
    private Boolean required = false;
    private Boolean value = false;
    private Boolean visited = false;

    public CheckBox(String id, String type, String label) {
        super(id, type, label);
    }
}
