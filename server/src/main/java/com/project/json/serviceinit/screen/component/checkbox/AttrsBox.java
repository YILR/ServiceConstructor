package com.project.json.serviceinit.screen.component.checkbox;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class AttrsBox {

    private List<Validation> validation;

    public void validation(){
        validation = new ArrayList<>();
        validation.add(new Validation());
    }
}
