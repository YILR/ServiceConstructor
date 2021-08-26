package com.project.json.serviceinit.screen.component.confirm;

import com.project.json.serviceinit.screen.component.Field;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AttrsConfirm {

    private List<Action> actions = new ArrayList<>();
    private String addrType;
    private String addressType;
    private Style style;
    private List<Field> fields = new ArrayList<>();


    public void confirmPersonalUserData() {
        actions.add(new Action("Изменить", "profileEdit", "editPassportData"));
        fields.add(new Field("firstName", ""));
        fields.add(new Field("lastName", ""));
        fields.add(new Field("middleName", ""));
        fields.add(new Field("birthDate", "Дата рождения"));
        fields.add(new Field("rfPasportSeries", "Серия паспорта"));
        fields.add(new Field("rfPasportNumber", "Номер паспорта"));
        fields.add(new Field("rfPasportIssueDate", "Дата выдачи"));
        fields.add(new Field("rfPasportIssuedBy", "Кем выдан"));
        fields.add(new Field("rfPasportIssuedById", "Код подразделения"));
    }

    public void regAddr(String addrType) {
        actions = null;
        this.addrType = addrType;
        this.style = new Style();
        fields.add(new Field("regAddr", "Адрес"));
    }

    public void regAddress(String addrType) {
        actions = null;
        this.addressType = addrType;
        fields.add(new Field("regAddr", "Адрес"));
    }

    public void confirmLegalData(String id) {
        actions.add(new Action("Изменить", "profileEdit", "editPassportData"));
        fields.add(new Field("fullName", "Полное наименование"));
        String ogrn = "ОГРН";
        if( id.equals("RecipientBusinessmanData") || id.equals("RepresentativeBusinessmanData"))
            ogrn = "ОГРНИП";

        fields.add(new Field("ogrn", ogrn));
        fields.add(new Field("inn", "ИНН"));
    }

}
