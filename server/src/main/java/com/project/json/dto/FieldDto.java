package com.project.json.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "field")
public class FieldDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;
    private String value;

    public FieldDto() {
    }

    public FieldDto(String label, String value) {
        this.label = label;
        this.value = value;
    }

    @Override
    public String toString() {
        return "FieldDto{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
