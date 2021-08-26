package com.project.json.serviceinit.screen.component.confirm;

import com.project.json.serviceinit.screen.component.AbstractComponent;
import lombok.Data;

@Data
public class ConfirmData extends AbstractComponent {

    private AttrsConfirm attrs = new AttrsConfirm();
    private String value = "";
    private Boolean visited = false;

    public ConfirmData(String id, String type, String label, String addrType) {
        this.id = id;
        this.type = type;
        this.label = label;
        addAction(addrType);
    }


    public void addAction(String addrType) {
        if (type.equals("ConfirmPersonalUserData")) {
            attrs.confirmPersonalUserData();
        } else if (type.equals("ConfirmPersonalUserPhone") || type.equals("ConfirmLegalPhone")) {
            attrs.setFields(null);
            attrs.getActions().add(new Action("Редактировать", "nextStepModal", "service/actions/editPhoneNumber"));
        } else if (type.equals("ConfirmPersonalUserEmail") || type.equals("ConfirmLegalEmail")) {
            attrs.setFields(null);
            attrs.getActions().add(new Action("Редактировать", "nextStepModal", "service/actions/editUserEmail"));
        } else if (type.equals("ConfirmPersonalUserRegAddr")) {
            attrs.regAddr(addrType);
        } else if (type.equals("RegistrationLegalAddr")){
            attrs.regAddress(addrType);
        }else if (type.equals("ConfirmLegalData")) {
            attrs.confirmLegalData(id);
        } else {
            attrs.setFields(null);
            attrs.getActions().add(new Action("", "nextStep", "getNextScreen"));
        }
    }

}
