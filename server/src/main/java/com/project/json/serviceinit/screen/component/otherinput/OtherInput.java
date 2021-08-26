package com.project.json.serviceinit.screen.component.otherinput;

import com.project.json.serviceinit.screen.component.AbstractComponent;
import lombok.Data;

import java.util.List;

@Data
public class OtherInput extends AbstractComponent {
    private Boolean required = true;
    private AttrsOther attrs;
    private String value = "";
    private Boolean visited = false;

    public OtherInput(String id, String type,String label, String suggestionId, ValidOther validation,  String[] mask) {
        this.id = id;
        this.type = type;
        this.label = label;
        this.suggestionId = suggestionId;
        attrs = new AttrsOther(validation, mask);
    }
}
