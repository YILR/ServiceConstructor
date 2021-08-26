package com.project.json.serviceinit.screen.component.infoscr;

import com.project.json.serviceinit.screen.component.AbstractComponent;
import lombok.Data;

@Data
public class InfoComponent extends AbstractComponent {

    private AttrsInfo attrs;

    public InfoComponent(String id) {
        this.id = id;
        this.label = "";
        setType("InfoScr");
    }

    public InfoComponent(String id, String label) {
        this.id = id;
        this.label = label;
        setType("InfoScr");
        attrs = new AttrsInfo();
    }
}
