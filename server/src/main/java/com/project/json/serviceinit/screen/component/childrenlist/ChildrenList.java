package com.project.json.serviceinit.screen.component.childrenlist;

import com.project.json.serviceinit.screen.component.AbstractComponent;
import lombok.Data;

import java.util.List;

@Data
public class ChildrenList extends AbstractComponent {

    private AttrsChild attrs;
    private String value = "";
    private Boolean visited = false;

    public ChildrenList(String id, String label, Boolean singleChild, Boolean isCycled, Integer minAge, Integer maxAge, List<String> idCopms) {
        this.id = id;
        this.type = "ChildrenList";
        this.label = label;
        this.attrs = new AttrsChild(singleChild, isCycled, minAge, maxAge, idCopms);
    }
}
