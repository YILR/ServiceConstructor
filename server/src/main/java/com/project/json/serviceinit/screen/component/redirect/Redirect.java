package com.project.json.serviceinit.screen.component.redirect;

import com.project.json.serviceinit.screen.component.AbstractComponent;
import lombok.Data;

@Data
public class Redirect extends AbstractComponent {

    private AttrsRed attrs;
    private String value = "";
    private Boolean visited = false;

    public Redirect(String id) {
        this.id = id;
        this.type = "Redirect";
        attrs = new AttrsRed();
    }

    public Redirect(String id, String link) {
        this.id = id;
        this.type = "Redirect";
        attrs = new AttrsRed(link);
    }

    public Redirect(String id, String label, String value) {
        this.id = id;
        this.type = "Redirect";
        attrs = new AttrsRed(label, value);
    }
}
