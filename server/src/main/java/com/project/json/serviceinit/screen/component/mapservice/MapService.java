package com.project.json.serviceinit.screen.component.mapservice;

import com.project.json.serviceinit.screen.component.AbstractComponent;
import lombok.Data;

@Data
public class MapService extends AbstractComponent {

    private AttrsMap attrs;

    public MapService(String id, String label, int code) {
        this.id = id;
        this.type = "MapService";
        this.label = label;
        attrs = new AttrsMap(code);
    }
}
