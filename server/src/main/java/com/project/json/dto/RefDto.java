package com.project.json.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ref")
public class RefDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String relatedRel;
    private String val;
    private String relation;

    public RefDto() {
    }

    public RefDto(String relatedRel, String relation, String val) {
        this.relatedRel = relatedRel;
        this.val = val;
        this.relation = relation;
    }

    @Override
    public String toString() {
        return "RefDto{" +
                "id=" + id +
                ", relatedRel='" + relatedRel + '\'' +
                ", val=" + val +
                ", relation='" + relation + '\'' +
                '}';
    }
}
