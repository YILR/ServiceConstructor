package com.project.json.serviceinit.screen;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class AbstractScreen {

    protected Boolean isFirstScreen;
    protected String id = "";
    protected String name = "";
    protected String type = "";
    protected String header = "";
    protected Suggestion suggestion;
    protected List<String> components = new ArrayList<>();

}
