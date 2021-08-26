package com.project.json.serviceinit.screen.component.adressinput;

import com.project.json.serviceinit.screen.component.AbstractComponent;
import com.project.json.serviceinit.screen.component.stringinput.AttrsInput;
import lombok.Data;

@Data
public class AddressInput extends AbstractComponent {

    private AttrsInput attrs = new AttrsInput();
    private Boolean required;
    private String value = "";
    private Boolean visited = false;

    public AddressInput(String id, String label, Boolean required) {
        this.id = id;
        this.type = "AddressInput";
        this.label = label;
        this.required = required;
    }
}
