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

}
