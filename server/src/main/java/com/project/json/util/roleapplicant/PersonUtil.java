package com.project.json.util.roleapplicant;

import com.project.json.dto.ComponentDto;
import com.project.json.dto.FieldDto;
import com.project.json.dto.RefDto;
import com.project.json.serviceinit.ServiceInit;
import com.project.json.serviceinit.screen.Screen;
import com.project.json.serviceinit.screen.rule.screenrule.ScreenRule;
import com.project.json.util.ServiceUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PersonUtil {

    public static void initFl(ServiceInit serviceInit, List<ScreenRule> screenRules){
        serviceInit.addScreen("s_FL_RecipientData", "Сведения о заявителе", "RecipientData", "ConfirmPersonalUserData", "Сведения о заявителе из ЕСИА", null, "s_FL_RecipientPhoneData");
        serviceInit.addScreen("s_FL_RecipientPhoneData", "Контактный телефон заявителя", "RecipientPhoneData", "ConfirmPersonalUserPhone", "Контактный телефон", null, "s_FL_RecipientEmailData");
        serviceInit.addScreen("s_FL_RecipientEmailData", "Электронная почта заявителя", "RecipientEmailData", "ConfirmPersonalUserEmail", "Электронная почта", null, "s_FL_RecipientRegAddressData");
        serviceInit.addScreen("s_FL_RecipientRegAddressData", "Адрес регистрации заявителя", "RecipientRegAddressData", "ConfirmPersonalUserRegAddr", "", "permanentRegistry", "s_FL_RecipientFactAddressData");
        serviceInit.addScreen("s_FL_RecipientFactAddressData", "Фактический адрес проживания заявителя", "RecipientFactAddressData", "ConfirmPersonalUserRegAddr", "Фактический адрес проживания", "actualResidence", "");
        serviceInit.put("s_FL_RecipientFactAddressData", screenRules);
    }

    public static void representativeFl(ServiceInit serviceInit, List<ScreenRule> screenRules) {
        serviceInit.addScreen("s_FL_RepresentativeData", "Сведения о представителе", "RepresentativeData", "ConfirmPersonalUserData", "Сведения о представителе из ЕСИА", null, "s_FL_RepresentativePhoneData");
        serviceInit.addScreen("s_FL_RepresentativePhoneData", "Контактный телефон представителя", "RepresentativePhoneData", "ConfirmPersonalUserPhone", "Контактный телефон", null, "s_FL_RepresentativeEmailData");
        serviceInit.addScreen("s_FL_RepresentativeEmailData", "Электронная почта представителя", "RepresentativeEmailData", "ConfirmPersonalUserEmail", "Электронная почта", null, "s_FL_RepresentativeRegAddressData");
        serviceInit.addScreen("s_FL_RepresentativeRegAddressData", "Адрес регистрации представителя", "RepresentativeRegAddressData", "ConfirmPersonalUserRegAddr", "", null, "s_FL_RepresentativeFactAddressData");
        serviceInit.addScreen("s_FL_RepresentativeFactAddressData", "Фактический адрес проживания представителя", "RepresentativeFactAddressData", "ConfirmPersonalUserRegAddr", "Фактический адрес проживания", "actualResidence", "s_IP_RecipientDataManual", "s_UL_RecipientDataManual", "s_FL_RecipientDataManual");
        if(screenRules != null)
            serviceInit.put("s_FL_RepresentativeFactAddressData", screenRules);
    }

    public static void applicantFl(ServiceInit serviceInit, List<ScreenRule> screenRules) {
        serviceInit.getScreens().add(new Screen("s_FL_RecipientDataManual", "CUSTOM", "Сведения о заявителе", "Далее", Arrays.asList("LastName", "FirstName", "MiddleName"), "doc_rec_pd"));
        ServiceUtil.addComponentsToFields(serviceInit.getApplicationFields(), Arrays.asList(
                new ComponentDto("LastName", "StringInput", "Фамилия", new FieldDto("(^[-а-яёЁА-Я\\ ]+$)|(^$)", "Разрешен ввод кириллицы, пробела, знака «-» (тире)"), "lastname",true),
                new ComponentDto("FirstName", "StringInput", "Имя", new FieldDto("(^[-а-яёЁА-Я\\ ]+$)|(^$)", "Разрешен ввод кириллицы, пробела, знака «-» (тире)"), "firstname",true),
                new ComponentDto("MiddleName", "StringInput", "Отчество", new FieldDto("(^[-а-яёЁА-Я\\ ]+$)|(^$)", "Разрешен ввод кириллицы, пробела, знака «-» (тире)"), "middlename",false)));
        serviceInit.put("s_FL_RecipientDataManual", Collections.singletonList(new ScreenRule("LastName", "s_FL_RecipientDataDocumentManual")));

        List<ComponentDto> componentDtos = createComponentsApplicant();
        serviceInit.getScreens().add(new Screen("s_FL_RecipientDataDocumentManual", "CUSTOM", "Документ, удостоверяющий личность заявителя", "Далее", ServiceUtil.mapIdComponents(componentDtos), "receiver_passp_dat"));
        ServiceUtil.addComponentsToFields(serviceInit.getApplicationFields(), componentDtos);
        serviceInit.put("s_FL_RecipientDataDocumentManual", Collections.singletonList(new ScreenRule("DocumentPersonal", "s_AuthorityDocument_FL")));

        serviceInit.getScreens().add(new Screen("s_AuthorityDocument_FL", "UNIQUE", "Загрузите документы", "Далее", "f_AuthorityDocument_FL"));
        ServiceUtil.addComponentsToFields(serviceInit.getApplicationFields(), Collections.singletonList(new ComponentDto("f_AuthorityDocument_FL", "FileUploadComponent", "Документ, подтверждающий полномочия представителя на подачу заявления от имени физического лица", "Необходимо загрузить: <br/> 1. Документ, подтверждающий полномочия представителя действовать от имени заявителя, подписанный усиленной квалифицированной электронной подписью заявителя или нотариуса. <br/> 2. Файл с открепленной усиленной квалифицированной электронной подписью заявителя или нотариуса в формате SIG.",
                "prev_files_power_att", new String[]{"PDF", "XML", "ZIP", "RAR", "SIG"}, 50, true)));
        serviceInit.put("s_AuthorityDocument_FL", screenRules);
    }

    private static List<ComponentDto> createComponentsApplicant(){
        List<ComponentDto> componentDtos = new ArrayList<>();
        componentDtos.add(new ComponentDto("DocumentPersonal", "Lookup", "Вид документа", "PGS_Types_document"));
        componentDtos.add(new ComponentDto("docseriesPassportRF", "StringInput", "Серия", "serial", new FieldDto("(^\\d{4}$)|(^$)", "Введите 4 цифры серии паспорта гражданина РФ"), true, Collections.singletonList(new RefDto("DocumentPersonal", "displayOn", "1")), true, "\\d \\d \\d \\d"));
        componentDtos.add(new ComponentDto("docnumberPassportRF", "StringInput", "Номер","number", new FieldDto("(^\\d{6}$)|(^$)", "Введите 6 цифр номера паспорта гражданина РФ"), true, Collections.singletonList(new RefDto("DocumentPersonal", "displayOn", "1")), true, "\\d \\d \\d \\d \\d \\d"));
        componentDtos.add(new ComponentDto("issueidPassportRF", "StringInput", "Код подразделения","issuer_code", new FieldDto("^([0-9]{3}-[0-9]{3})$|(^$)", "Введите значение в формате: 999-999, где 9 - цифра"), true, Collections.singletonList(new RefDto("DocumentPersonal", "displayOn", "1")), true, "\\d \\d \\d - \\d \\d \\d"));
        componentDtos.add(new ComponentDto("issuedatePassportRF", "DateInput", "Дата выдачи",  "issue_date",true, Collections.singletonList(new RefDto("DocumentPersonal", "displayOn", "1")), true));
        componentDtos.add(new ComponentDto("issueorgPassportRF", "StringInput", "Кем выдан",  "issued",false, Collections.singletonList(new RefDto("DocumentPersonal", "displayOn", "1")), true));
        componentDtos.add(new ComponentDto("docseries", "StringInput", "Серия", false, Collections.singletonList(new RefDto("DocumentPersonal", "displayOn", "2 3 4 5 6 7 8 9 10 11 12 13 14 15 16")), false));
        componentDtos.add(new ComponentDto("docnumber", "StringInput", "Номер",  true, Collections.singletonList(new RefDto("DocumentPersonal", "displayOn", "2 3 4 5 6 7 8 9 10 11 12 13 14 15 16")), true));
        componentDtos.add(new ComponentDto("issuedate", "DateInput", "Дата выдачи", true, Collections.singletonList(new RefDto("DocumentPersonal", "displayOn", "2 3 4 5 6 7 8 9 10 11 12 13 14 15 16")), true));
        componentDtos.add(new ComponentDto("issueorg", "StringInput", "Кем выдан", false, Collections.singletonList(new RefDto("DocumentPersonal", "displayOn", "2 3 4 5 6 7 8 9 10 11 12 13 14 15 16")), true));
        return componentDtos;
    }
}
