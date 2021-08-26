package com.project.json.serviceinit.screen.component;

import lombok.Data;

@Data
public abstract class AbstractComponent {

    protected String id = "";
    protected String type = "";
    protected String label = "";
    protected String suggestionId;

}
