package com.project.json.serviceinit.screen.component.textarea;

import com.project.json.serviceinit.screen.component.AbstractComponent;
import com.project.json.serviceinit.screen.component.stringinput.AttrsInput;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TextArea extends AbstractComponent {
    private Boolean required;
    private AttrsInput attrs = new AttrsInput();
    private String value = "";
    private Boolean visited = false;

    public TextArea(String id, String type, String label, String charsAmount, String suggestionId, Boolean required) {
        super(id, type, label);
        this.suggestionId = suggestionId;
        attrs.charsAmount(charsAmount);
        this.required = required;
    }
}
