package com.project.json.serviceinit.screen;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Screen extends AbstractScreen {

    private List<Button> buttons;
    private List<String> logicComponents;
    private Boolean isTerminal;

    public Screen(String id, String type, String header, String button, String idComp) {
        this.id = id;
        this.type = type;
        this.header = header;
        this.name = header;
        if(button != null){
            buttons = new ArrayList<>();
            buttons.add(new Button(button));
        }
        components.add(idComp);
    }

    public Screen(String id, String type, String header, String button, String idComp, String groupId) {
        this.id = id;
        this.type = type;
        this.header = header;
        this.name = header;
        if(button != null){
            buttons = new ArrayList<>();
            buttons.add(new Button(button));
        }
        components.add(idComp);
        suggestion = groupId == null ? null : new Suggestion(groupId);
    }


    public Screen(Boolean isFirstScreen, String id, String type, String header, String button, String idComp) {
        this.isFirstScreen = isFirstScreen;
        this.id = id;
        this.type = type;
        this.header = header;
        this.name = header;
        if(button != null){
            buttons = new ArrayList<>();
            buttons.add(new Button(button));
        }
        components.add(idComp);
    }

    public Screen(String id, String type, String header, String button, List<String> idComp) {
        this.id = id;
        this.type = type;
        this.header = header;
        this.name = header;
        if(button != null){
            buttons = new ArrayList<>();
            buttons.add(new Button(button));
        }
        components.addAll(idComp);
    }

    public Screen(String id, String type, String header, String button, List<String> idComp, String groupId) {
        this.id = id;
        this.type = type;
        this.header = header;
        this.name = header;
        if(button != null){
            buttons = new ArrayList<>();
            buttons.add(new Button(button));
        }
        components.addAll(idComp);
        suggestion = groupId == null ? null : new Suggestion(groupId);
    }

    public void logic(){
        logicComponents = new ArrayList<>();
        logicComponents.add("CheckRegion");
    }

    public void hasTerminal(){
        isTerminal = false;
    }

}
