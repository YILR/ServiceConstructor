package com.project.json.serviceinit.screen.component.dropdown;

import com.project.json.dto.FieldDto;
import com.project.json.serviceinit.screen.component.AbstractComponent;
import lombok.Data;

import java.util.List;

@Data
public class DropDown extends AbstractComponent {

    private AttrsDrop attrs;

    private String value = "";
    private Boolean visited = false;

    public DropDown(String id, String label, List<FieldDto> fieldDtos, Boolean required) {
        this.id = id;
        this.type = "DropDown";
        this.label = label;
        this.attrs = new AttrsDrop(fieldDtos, required);
    }

}
