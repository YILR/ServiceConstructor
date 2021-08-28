package com.project.json.serviceinit.screen.component.stringinput;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.json.dto.FieldDto;
import com.project.json.dto.RefDto;
import com.project.json.util.RefUtil;
import com.project.json.util.jsonserialize.CustomListSerializer;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class AttrsInput {

    private String fstuc;
    private String customUnrecLabel;
    private List<Map<String, String>> fields;
    private String charsAmount;
    private String hint;
    private String grid;
    @JsonSerialize(using = CustomListSerializer.class)
    private List<String> mask;
    private List<Validation> validation;
    private List<Ref> ref;
    private String placeholder;
    private Boolean hidden;

    public void stringInit(String hint, List<FieldDto> valid, String mask) {
        this.hint = hint;
        validMap(valid);
        maskMap(mask);
    }

    public void setFields(String fieldName) {
        if (fieldName != null && !fieldName.isEmpty()) {
            fields = new ArrayList<>();
            fields.add(new LinkedHashMap<>());
            fields.get(0).put("fieldName", fieldName);
        }
    }

    public void setHidden(Boolean hidden){
        if(hidden != null && hidden)
            this.hidden = true;
    }

    public void fstuc() {
        fstuc = "all";
    }

    public void grid() {
        grid = "grid-col-6 grid-col-12-sm";
    }

    public void charsAmount(String charsAmount) {
        this.charsAmount = charsAmount;
        validation = new ArrayList<>();
        validation.add(new Validation(".+", "Поле не может быть пустым"));
    }

    public void putRef(List<RefDto> refDtos) {
        ref = refDtos.stream()
                .map(refDto -> new Ref(refDto.getRelatedRel(), refDto.getRelation(), RefUtil.mapRefVal(refDto.getVal())))
                .collect(Collectors.toList());
    }

    private void validMap(List<FieldDto> valid) {
        if (Objects.nonNull(valid)) {
            validation = valid.stream().filter(fieldDto -> fieldDto.getLabel() != null && !fieldDto.getLabel().equals("null"))
                    .map(fieldDto -> new Validation(fieldDto.getLabel(), fieldDto.getValue())).collect(Collectors.toList());
            if (validation.isEmpty())
                validation = null;
        }
    }

    private void maskMap(String mask) {
        if (Objects.nonNull(mask)) {
            String[] masks = mask.trim().split(" ");
            if (masks.length > 1) {
                this.mask = Arrays.stream(masks).map(m -> {
                    if (m.startsWith("\\"))
                        return "/" + m + "/";
                    else if (m.isEmpty())
                        return " ";
                    return m;
                }).collect(Collectors.toList());
            } else {
                this.mask = new ArrayList<>();
                this.mask.add(mask);
            }
        }
    }

}
