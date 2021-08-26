package com.project.json.serviceinit.screen.component.stringinput;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.json.util.jsonserialize.CustomListSerializer;
import lombok.Data;

import java.util.List;

@Data
public class Ref<T> {

    private String relatedRel;
    @JsonSerialize(using = CustomListSerializer.class)
    private List<T> val;
    private String relation;

    public Ref(String relatedRel, String relation, List<T> val) {
        this.relatedRel = relatedRel;
        this.val = val;
        this.relation = relation;
    }
}
