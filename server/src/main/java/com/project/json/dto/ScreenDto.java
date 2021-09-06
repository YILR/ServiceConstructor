package com.project.json.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;


@Data
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "screen")
public class ScreenDto {

    @Id
    private String id;
    private String type;
    private String header;
    @OneToOne(cascade = ALL)
    private ChildDto child;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToMany(cascade = ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "scr_comp", joinColumns = @JoinColumn(name = "screen_id"), inverseJoinColumns = @JoinColumn(name = "component_id"))
    private List<ComponentDto> components = new ArrayList<>();
    private String suggestion;
    @Transient
    private List<ScreenRuleDto> screenRules;
    @Transient
    private List<CycleScreenRuleDto> cycleScreenRules;

    public ScreenDto() {
    }

    public ScreenDto(String id, String type, String header, List<ComponentDto> components, List<ScreenRuleDto> screenRules) {
        this.id = id;
        this.type = type;
        this.header = header;
        this.components = components;
        this.screenRules = screenRules;
    }

    @Override
    public String toString() {
        return "ScreenDto{" +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", header='" + header + '\'' +
                ", child='" + child + '\'' +
                ", components=" + components +
                ", screenRules=" + screenRules +
                ", cycleScreenRules=" + cycleScreenRules +
                '}';
    }
}
