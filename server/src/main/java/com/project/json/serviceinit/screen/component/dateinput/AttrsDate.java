package com.project.json.serviceinit.screen.component.dateinput;

import com.project.json.dto.RefDto;
import com.project.json.serviceinit.screen.component.stringinput.Ref;
import com.project.json.serviceinit.screen.component.stringinput.Validation;
import com.project.json.util.RefUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class AttrsDate {
    private List<Map<String, String>> fields;
    private String grid = "grid-col-6 grid-col-12-sm";
    private String accuracy = "day";
    private String minDate = "-130y";
    private String maxDate = "today";
    private String brokenDateFixStrategy = "reset";
    private List<Validation> validation = new ArrayList<>();
    private List<Ref> ref;

    public AttrsDate() {
        validation.add(new Validation(".+", "Поле не может быть пустым"));
    }

    public void setFields(String fieldName) {
        if (fieldName != null && fieldName.isEmpty()) {
            fields = new ArrayList<>();
            fields.add(new LinkedHashMap<>());
            fields.get(0).put("fieldName", fieldName);
        }
    }

    public void putRef(List<RefDto> refDtos){
        ref = refDtos.stream()
                .map(refDto -> new Ref(refDto.getRelatedRel(), refDto.getRelation(), RefUtil.mapRefVal(refDto.getVal())))
                .collect(Collectors.toList());
    }
}
