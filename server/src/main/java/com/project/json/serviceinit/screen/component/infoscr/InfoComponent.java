package com.project.json.serviceinit.screen.component.infoscr;

import com.project.json.serviceinit.screen.component.AbstractComponent;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class InfoComponent extends AbstractComponent {

    private AttrsInfo attrs;

    public InfoComponent(String id) {
        super(id, "InfoScr", "");
    }

    public InfoComponent(String id, String label) {
        super(id, "InfoScr", label);
        attrs = new AttrsInfo();
    }
}
