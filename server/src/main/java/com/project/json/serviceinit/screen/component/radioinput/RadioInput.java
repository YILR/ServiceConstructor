package com.project.json.serviceinit.screen.component.radioinput;

import com.project.json.dto.FieldDto;
import com.project.json.serviceinit.screen.component.AbstractComponent;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class RadioInput extends AbstractComponent {

    private Boolean required;
    private AttrsRadio attrs;
    private String value = "";
    private Boolean visited = false;


    public RadioInput(String id, String type, String label, Boolean required, String fieldName, List<FieldDto> fieldDtos, Boolean hidden) {
        super(id, type, label);
        this.required = required;
        this.attrs = new AttrsRadio(fieldName, fieldDtos, hidden);
    }


}
