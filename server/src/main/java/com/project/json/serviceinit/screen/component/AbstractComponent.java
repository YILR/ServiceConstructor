package com.project.json.serviceinit.screen.component;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
public abstract class AbstractComponent {

    protected String id = "";
    protected String type = "";
    protected String label = "";
    protected String suggestionId;

    public AbstractComponent() {
    }

    public AbstractComponent(String id, String type, String label) {
        this.id = id;
        this.type = type;
        this.label = label;
    }

}
