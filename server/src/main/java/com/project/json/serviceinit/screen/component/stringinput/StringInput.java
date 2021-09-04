package com.project.json.serviceinit.screen.component.stringinput;

import com.project.json.dto.FieldDto;
import com.project.json.serviceinit.screen.component.AbstractComponent;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class StringInput extends AbstractComponent {

    private Boolean required;
    private AttrsInput attrs = new AttrsInput();
    private String value = "";
    private Boolean visited = false;


    public StringInput(String id, String type, String label, String hint, List<FieldDto> valid, String mask, String suggestionId,Boolean required) {
        super(id, type, label);
        this.suggestionId = suggestionId;
        this.required = required;
        attrs.stringInit(hint, valid, mask);
        if(label.equalsIgnoreCase("Отчество"))
            attrs.setCustomUnrecLabel("При наличии");
        if(type.equals("SnilsInput"))
            attrs.setPlaceholder("000-000-000 00");
    }

}
