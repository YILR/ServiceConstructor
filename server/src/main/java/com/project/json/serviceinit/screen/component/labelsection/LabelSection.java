package com.project.json.serviceinit.screen.component.labelsection;

import com.project.json.serviceinit.screen.component.AbstractComponent;
import com.project.json.serviceinit.screen.component.stringinput.AttrsInput;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LabelSection extends AbstractComponent {

    private AttrsInput attrs = new AttrsInput();
    private String value = "";
    private Boolean visited = false;

    public LabelSection(String id, String type, String label) {
        super(id, type, label);
    }


}
