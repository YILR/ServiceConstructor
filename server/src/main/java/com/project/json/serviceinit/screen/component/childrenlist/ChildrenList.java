package com.project.json.serviceinit.screen.component.childrenlist;

import com.project.json.serviceinit.screen.component.AbstractComponent;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ChildrenList extends AbstractComponent {

    private AttrsChild attrs;
    private String value = "";
    private Boolean visited = false;

    public ChildrenList(String id, String label, Boolean singleChild, Boolean isCycled, Integer minAge, Integer maxAge, List<String> idCopms) {
        super(id, "ChildrenList", label);
        this.attrs = new AttrsChild(singleChild, isCycled, minAge, maxAge, idCopms);
    }
}
