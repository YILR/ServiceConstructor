package com.project.json.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.CascadeType.*;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "component")
public class ComponentDto {

    @Id
    private String id;
    private String type;
    private String label;
    private String suggestionId;
    private Boolean required;
    @OneToMany(cascade = ALL)
    private List<FieldDto> field;
    private String hint;
    private String mask;
    @ElementCollection
    @CollectionTable(name = "file_type_mapping",
        joinColumns = {@JoinColumn(name = "file_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "file_type")
    @Column(name = "value")
    private Map<String, Boolean> fileType;
    private String fieldName;
    private Integer maxSize;
    private String position;
    private String dictionaryType;
    private Boolean grid = false;
    @OneToMany(cascade = CascadeType.ALL)
    private List<RefDto> ref;
    private String charsAmount;
    private Boolean atLeastOne = false;
    private Boolean hidden;
    @ManyToMany(cascade = {PERSIST, MERGE}, mappedBy = "components")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<ScreenDto> screens;

    public ComponentDto() {
    }


    public ComponentDto(String id, String type, String label) {
        this.id = id;
        this.type = type;
        this.label = label;
    }

    public ComponentDto(String id, String type, String label, String dictionaryType) {
        this.id = id;
        this.type = type;
        this.label = label;
        this.dictionaryType = dictionaryType;
    }

    public ComponentDto(String id, String type, String label, String hint,String suggestionId, String[] fileTypes, Integer maxSize, Boolean required) {
        this.id = id;
        this.type = type;
        this.label = label;
        this.hint = hint;
        this.suggestionId = suggestionId;
        buildFileType();
        Arrays.stream(fileTypes).forEach(fileType -> this.fileType.put(fileType, true));
        this.maxSize = maxSize;
        this.required = required;
    }

    public ComponentDto(String id, String type, String label, List<FieldDto> fieldDto) {
        this.id = id;
        this.type = type;
        this.label = label;
        field = fieldDto;
    }

    public ComponentDto(String id, String type, String label, Boolean grid, List<RefDto> ref, Boolean required) {
        this.id = id;
        this.type = type;
        this.label = label;
        this.grid = grid;
        this.ref = ref;
        this.required = required;
    }

    public ComponentDto(String id, String type, String label, String suggestionId, Boolean grid, List<RefDto> ref, Boolean required) {
        this.id = id;
        this.type = type;
        this.label = label;
        this.suggestionId = suggestionId;
        this.grid = grid;
        this.ref = ref;
        this.required = required;
    }

    public ComponentDto(String id, String type, String label, String suggestionId, FieldDto fieldDto, Boolean grid, List<RefDto> ref, Boolean required, String mask) {
        this.id = id;
        this.type = type;
        this.label = label;
        this.suggestionId = suggestionId;
        field = new ArrayList<>();
        field.add(fieldDto);
        this.grid = grid;
        this.ref = ref;
        this.required = required;
        this.mask = mask;
    }

    public ComponentDto(String id, String type, String label, FieldDto fieldDto, String suggestionId, Boolean required) {
        this.id = id;
        this.type = type;
        this.label = label;
        field = new ArrayList<>();
        field.add(fieldDto);
        this.suggestionId = suggestionId;
        this.required = required;
    }

    public ComponentDto(String id, String type, String label, List<FieldDto> fieldDto, List<RefDto> refDtos, String position, Boolean required) {
        this.id = id;
        this.type = type;
        this.label = label;
        field = fieldDto;
        ref = refDtos;
        this.position = position;
        this.required = required;
    }

    public void buildFileType(){
        fileType = new LinkedHashMap<>();
    }

    @Override
    public String toString() {
        return "ComponentDto{" +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", label='" + label + '\'' +
                ", required=" + required +
                ", hint='" + hint + '\'' +
                ", mask=" + mask +
                ", fileType=" + fileType +
                ", maxSize=" + maxSize +
                ", position='" + position + '\'' +
                ", dictionaryType='" + dictionaryType + '\'' +
                ", grid='" + grid + '\'' +
                ", charsAmount='" + charsAmount + '\'' +
                '}';
    }
}
