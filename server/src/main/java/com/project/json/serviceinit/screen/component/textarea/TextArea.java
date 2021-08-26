package com.project.json.serviceinit.screen.component.textarea;

import com.project.json.serviceinit.screen.component.AbstractComponent;
import com.project.json.serviceinit.screen.component.stringinput.AttrsInput;
import lombok.Data;

@Data
public class TextArea extends AbstractComponent {
    private Boolean required;
    private AttrsInput attrs = new AttrsInput();
    private String value = "";
    private Boolean visited = false;

    public TextArea(String id, String label, String charsAmount, String suggestionId, Boolean required) {
        this.id = id;
        this.type = "TextArea";
        this.label = label;
        this.suggestionId = suggestionId;
        attrs.charsAmount(charsAmount);
        this.required = required;
    }
}
