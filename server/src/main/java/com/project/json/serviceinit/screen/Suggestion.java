package com.project.json.serviceinit.screen;

import lombok.Data;

@Data
public class Suggestion {

    private String groupId;

    public Suggestion(String groupId) {
        this.groupId = groupId;
    }
}
