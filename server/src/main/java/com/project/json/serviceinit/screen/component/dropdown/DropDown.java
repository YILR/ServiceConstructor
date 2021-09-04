package com.project.json.serviceinit.screen.component.dropdown;

import com.project.json.dto.FieldDto;
import com.project.json.serviceinit.screen.component.AbstractComponent;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class DropDown extends AbstractComponent {

    private AttrsDrop attrs;

    private String value = "";
    private Boolean visited = false;

    public DropDown(String id, String type, String label, List<FieldDto> fieldDtos, Boolean required) {
        super(id, type, label);
        this.attrs = new AttrsDrop(fieldDtos, required);
    }

}
