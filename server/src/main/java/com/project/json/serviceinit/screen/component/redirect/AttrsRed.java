package com.project.json.serviceinit.screen.component.redirect;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AttrsRed {

    private List<ActionRed> actions ;
    private String link;

    public AttrsRed() {
        actions = new ArrayList<>();
        actions.add(new ActionRed());
    }

    public AttrsRed(String link) {
        this.link = link;
    }

    public AttrsRed(String label, String value) {
        actions = new ArrayList<>();
        actions.add(new ActionRed(label, value));
    }

}
