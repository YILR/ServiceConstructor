package com.project.json.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ChildDto {
    @Id
    private String id;
    private String label;
    private Integer minAge;
    private Integer maxAge;
    private Boolean singleChild;
    private Boolean isCycled;

    @Override
    public String toString() {
        return "ChildDto{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", minAge=" + minAge +
                ", maxAge=" + maxAge +
                ", singleChild=" + singleChild +
                ", isCycled=" + isCycled +
                '}';
    }
}
