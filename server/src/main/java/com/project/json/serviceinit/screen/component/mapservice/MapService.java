package com.project.json.serviceinit.screen.component.mapservice;

import com.project.json.serviceinit.screen.component.AbstractComponent;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MapService extends AbstractComponent {

    private AttrsMap attrs;

    public MapService(String id, String label, int code) {
        super(id, "MapService", label);
        attrs = new AttrsMap(code);
    }
}
