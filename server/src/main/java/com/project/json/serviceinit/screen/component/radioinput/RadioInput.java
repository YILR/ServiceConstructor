package com.project.json.serviceinit.screen.component.radioinput;

import com.project.json.dto.FieldDto;
import com.project.json.serviceinit.screen.component.AbstractComponent;
import lombok.Data;

import java.util.List;

@Data
public class RadioInput extends AbstractComponent {


    private AttrsRadio attrs;
    private Boolean required;
    private String value = "";
    private Boolean visited = false;


    public RadioInput(String id, String label, Boolean required, String fieldName, List<FieldDto> fieldDtos, Boolean hidden) {
        this.id = id;
        this.type = "RadioInput";
        this.label = label;
        this.required = required;
        this.attrs = new AttrsRadio(fieldName, fieldDtos, hidden);
    }


}
