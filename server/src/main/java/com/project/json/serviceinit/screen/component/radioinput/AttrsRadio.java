package com.project.json.serviceinit.screen.component.radioinput;

import com.project.json.dto.FieldDto;
import com.project.json.dto.RefDto;
import com.project.json.serviceinit.screen.component.stringinput.Ref;
import com.project.json.util.RefUtil;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class AttrsRadio {

    private List<Map<String, String>> fields;
    private List<Support> supportedValues;
    private List<Ref> ref;
    private String defaultValue;
    private Boolean isHorizontal;
    private Boolean isVertical;
    private Boolean hidden;


    public AttrsRadio(String fieldName, List<FieldDto> fieldDtos, Boolean hidden) {
        if(fieldName != null && !fieldName.isEmpty()){
            fields = new ArrayList<>();
            fields.add(new LinkedHashMap<>());
            fields.get(0).put("fieldName", fieldName);
            if(fieldName.equals("isNew"))
               defaultValue = "true";
        }
        this.hidden = hidden;
        this.supportedValues = fieldDtos.stream()
                .map(fieldDto -> new Support(fieldDto.getLabel(), fieldDto.getValue()))
                .collect(Collectors.toList());
    }

    public void putRef(List<RefDto> refDtos){
        ref = refDtos.stream()
                .map(refDto -> new Ref(refDto.getRelatedRel(), refDto.getRelation(), RefUtil.mapRefVal(refDto.getVal())))
                .collect(Collectors.toList());
    }
}
