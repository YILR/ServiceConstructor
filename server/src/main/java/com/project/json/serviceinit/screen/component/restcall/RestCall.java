package com.project.json.serviceinit.screen.component.restcall;

import com.project.json.serviceinit.screen.component.AbstractComponent;
import lombok.Data;

@Data
public class RestCall extends AbstractComponent {

    private AttrsRest attrs;

    public RestCall(String id, String type, int code) {
        this.id = id;
        this.type = type;
        this.label = null;
        this.attrs = new AttrsRest(code);
    }
}
