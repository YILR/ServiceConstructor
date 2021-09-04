package com.project.json.serviceinit.screen.component.dateinput;

import com.project.json.serviceinit.screen.component.AbstractComponent;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DateInput extends AbstractComponent {

    private Boolean required;
    private AttrsDate attrs = new AttrsDate();

    private String value = "";
    private Boolean visited = false;

    public DateInput(String id, String type, String label, String suggestionId, Boolean required) {
        super(id, type, label);
        this.suggestionId = suggestionId;
        this.required = required;
    }


}
