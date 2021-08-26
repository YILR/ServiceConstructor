package com.project.json.serviceinit.screen.component.dropdown;

import com.project.json.dto.FieldDto;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class AttrsDrop {

    private String grid;
    private List<Dictionary> dictionaryList;
    private Boolean required;

    public AttrsDrop(List<FieldDto> fieldDtos, Boolean required) {
        this.dictionaryList = fieldDtos.stream().
                map(fieldDto -> new Dictionary(fieldDto.getLabel(), fieldDto.getValue()))
                .collect(Collectors.toList());
        this.required = required;
    }

    public void grid(){
        grid = "grid-col-6 grid-col-12-sm";
    }
}
