package com.project.json.serviceinit.screen.component.adressinput;

import com.project.json.serviceinit.screen.component.AbstractComponent;
import com.project.json.serviceinit.screen.component.stringinput.AttrsInput;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AddressInput extends AbstractComponent {

    private AttrsInput attrs = new AttrsInput();
    private Boolean required;
    private String value = "";
    private Boolean visited = false;

    public AddressInput(String id, String type, String label, Boolean required) {
        super(id, type, label);
        this.required = required;
    }
}
