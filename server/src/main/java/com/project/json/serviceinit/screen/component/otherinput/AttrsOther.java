package com.project.json.serviceinit.screen.component.otherinput;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AttrsOther {

    private List<ValidOther> validation = new ArrayList<>();
    private String[] mask;

    public AttrsOther(ValidOther validation, String[] mask) {
        this.validation.add(validation);
        this.validation.add(new ValidOther("validation-fn", "Такого номера не существует"));
        this.mask = mask;
    }
}
