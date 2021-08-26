package com.project.json.serviceinit.screen.component.dateinput;

import com.project.json.serviceinit.screen.component.AbstractComponent;
import lombok.Data;

@Data
public class DateInput extends AbstractComponent {

    private Boolean required;
    private AttrsDate attrs = new AttrsDate();

    private String value = "";
    private Boolean visited = false;

    public DateInput(String id, String label, String suggestionId,Boolean required) {
        this.id = id;
        this.type = "DateInput";
        this.label = label;
        this.suggestionId = suggestionId;
        this.required = required;
    }


}
