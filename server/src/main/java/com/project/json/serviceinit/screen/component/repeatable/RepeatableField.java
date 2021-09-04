package com.project.json.serviceinit.screen.component.repeatable;

import com.project.json.serviceinit.screen.component.AbstractComponent;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class RepeatableField extends AbstractComponent {

    private AttrsRep attrs;
    private String value = "";
    private Boolean visited = false;

    public RepeatableField(String id, List<String> idCopms) {
        this.id = id;
        this.type = "RepeatableFields";
        this.label = "Добавить";
        attrs = new AttrsRep(idCopms);
    }
}
