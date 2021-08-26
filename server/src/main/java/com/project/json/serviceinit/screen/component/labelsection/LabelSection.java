package com.project.json.serviceinit.screen.component.labelsection;

import com.project.json.serviceinit.screen.component.AbstractComponent;
import com.project.json.serviceinit.screen.component.stringinput.AttrsInput;
import lombok.Data;

@Data
public class LabelSection extends AbstractComponent {

    private AttrsInput attrs = new AttrsInput();
    private String value = "";
    private Boolean visited = false;

    public LabelSection(String id, String label) {
        this.id = id;
        this.type = "LabelSection";
        this.label = label;
    }
}
