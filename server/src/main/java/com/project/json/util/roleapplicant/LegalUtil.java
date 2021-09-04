package com.project.json.util.roleapplicant;

import com.project.json.dto.ComponentDto;
import com.project.json.serviceinit.ServiceInit;
import com.project.json.serviceinit.screen.Screen;
import com.project.json.serviceinit.screen.component.AbstractComponent;
import com.project.json.serviceinit.screen.component.otherinput.OtherInput;
import com.project.json.serviceinit.screen.component.otherinput.ValidOther;
import com.project.json.serviceinit.screen.component.textarea.TextArea;
import com.project.json.serviceinit.screen.rule.screenrule.ScreenRule;
import com.project.json.util.ServiceUtil;

import java.util.*;

public class LegalUtil {

    private static ServiceInit serviceInit;

    public static void initUl(ServiceInit serviceInit, List<ScreenRule> screenRules) {
        LegalUtil.serviceInit = serviceInit;
        serviceInit.addScreen("s_UL_RecipientData", "Сведения о заявителе", "RecipientLegalData", "ConfirmLegalData", "", null, "s_UL_RecipientPhoneData");
        serviceInit.addScreen("s_UL_RecipientPhoneData", "Контактный телефон заявителя", "RecipientLegalPhoneData", "ConfirmLegalPhone", "Контактный телефон", null, "s_UL_RecipientEmailData");
        serviceInit.addScreen("s_UL_RecipientEmailData", "Электронная почта заявителя", "RecipientLegalEmailData", "ConfirmLegalEmail", "Электронная почта", null, "s_UL_RecipientRegAddressData");
        serviceInit.addScreen("s_UL_RecipientRegAddressData", "Почтовый адрес заявителя", "RecipientLegalRegAddressData", "RegistrationLegalAddr", "", "legalAddress", "s_UL_RecipientEmployeeData");
        serviceInit.addScreen("s_UL_RecipientEmployeeData", "Сведения об уполномоченном лице", "RecipientData", "ConfirmPersonalUserData", "Сведения о заявителе из ЕСИА", null, "s_UL_RecipientEmployeePhoneData");
        serviceInit.addScreen("s_UL_RecipientEmployeePhoneData", "Контактный телефон уполномоченного лица", "RecipientPhoneData", "ConfirmPersonalUserPhone", "Контактный телефон", null, "s_UL_RecipientEmployeeEmailData");
        serviceInit.addScreen("s_UL_RecipientEmployeeEmailData", "Электронная почта уполномоченного лица", "RecipientEmailData", "ConfirmPersonalUserEmail", "Электронная почта", null, "s_AuthorityDocument_Employee");
        addUL_RecipientScreen(screenRules);
        serviceInit.getScreens().add(new Screen("s_AuthorityDocument_Employee", "UNIQUE", "Загрузите документы", "Далее", "f_AuthorityDocument_Employee"));
        ServiceUtil.addComponentsToFields(serviceInit.getApplicationFields(), Collections.singletonList(new ComponentDto("f_AuthorityDocument_Employee", "FileUploadComponent", "Документ, подтверждающий полномочия сотрудника действовать от имени юридического лица","Необходимо загрузить: <br/>1. Документ, подтверждающий полномочия представителя действовать от имени заявителя, подписанный усиленной квалифицированной электронной подписью заявителя или нотариуса. <br/>2. Файл с открепленной усиленной квалифицированной электронной подписью заявителя или нотариуса в формате SIG.",
                "prev_files_power_att", new String[]{"PDF", "XML", "ZIP", "RAR", "SIG"}, 50, true)));
        serviceInit.put("s_AuthorityDocument_Employee", screenRules);
    }

    public static void representativeUl(ServiceInit serviceInit, List<ScreenRule> screenRules) {
        serviceInit.addScreen("s_UL_RepresentativeData", "Сведения о представителе", "RepresentativeLegalData", "ConfirmLegalData", "", null, "s_UL_RepresentativePhoneData");
        serviceInit.addScreen("s_UL_RepresentativePhoneData", "Контактный телефон представителя", "RepresentativeLegalPhoneData", "ConfirmLegalPhone", "Контактный телефон", null, "s_UL_RepresentativeEmailData");
        serviceInit.addScreen("s_UL_RepresentativeEmailData", "Электронная почта представителя", "RepresentativeLegalEmailData", "ConfirmLegalEmail", "Электронная почта", null, "s_UL_RepresentativeRegAddressData");
        serviceInit.addScreen("s_UL_RepresentativeRegAddressData", "Почтовый адрес представителя", "RepresentativeLegalRegAddressData", "RegistrationLegalAddr", "", "legalAddress", "s_UL_RepresentativeEmployeeData");
        serviceInit.addScreen("s_UL_RepresentativeEmployeeData", "Сведения об уполномоченном лице", "RepresentativeData", "ConfirmPersonalUserData", "Сведения о представителе из ЕСИА", null, "s_UL_RepresentativeEmployeePhoneData");
        serviceInit.addScreen("s_UL_RepresentativeEmployeePhoneData", "Контактный телефон уполномоченного лица", "RepresentativePhoneData", "ConfirmPersonalUserPhone", "Контактный телефон", null, "s_UL_RepresentativeEmployeeEmailData");
        serviceInit.addScreen("s_UL_RepresentativeEmployeeEmailData", "Электронная почта уполномоченного лица", "RepresentativeEmailData", "ConfirmPersonalUserEmail", "Электронная почта", null, "s_IP_RecipientDataManual", "s_UL_RecipientDataManual", "s_FL_RecipientDataManual");
        if(screenRules != null)
            serviceInit.put("s_UL_RepresentativeEmployeeEmailData", screenRules);
    }

    public static void applicantUl(ServiceInit serviceInit, List<ScreenRule> screenRules) {
        Set<AbstractComponent> applicationFields = serviceInit.getApplicationFields();
        serviceInit.getScreens().add(new Screen("s_UL_RecipientDataManual", "CUSTOM", "Сведения о заявителе", "Далее", Arrays.asList("Fullname", "OGRN", "INN"), "legal_data"));
        applicationFields.add(new TextArea("Fullname","TextArea", "Полное наименование", "2000", "org_full_name",true));
        applicationFields.add(new OtherInput("OGRN", "OgrnInput", "ОГРН", "org_ogrn",new ValidOther(null, "Указан некорректный ОГРН"),  new String[]{"/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/",}));
        applicationFields.add(new OtherInput("INN", "LegalInnInput", "ИНН", "org_inn",new ValidOther(null, "Указан некорректный ИНН"),  new String[]{"/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/"}));
        serviceInit.put("s_UL_RecipientDataManual", Collections.singletonList(new ScreenRule("Fullname", "s_AuthorityDocument_UL")));

        serviceInit.getScreens().add(new Screen("s_AuthorityDocument_UL", "UNIQUE", "Загрузите документы", "Далее", "f_AuthorityDocument_UL"));
        ServiceUtil.addComponentsToFields(applicationFields, Collections.singletonList(new ComponentDto("f_AuthorityDocument_UL", "FileUploadComponent", "Документ, подтверждающий полномочия представителя на подачу заявления от имени юридического лица","Необходимо загрузить:<br/> 1. Документ, подтверждающий полномочия представителя действовать от имени заявителя, подписанный усиленной квалифицированной электронной подписью заявителя или нотариуса.<br/> 2. Файл с открепленной усиленной квалифицированной электронной подписью заявителя или нотариуса в формате SIG.",
                "prev_files_power_att", new String[]{"PDF", "XML", "ZIP", "RAR", "SIG"}, 50, true)));
        serviceInit.put("s_AuthorityDocument_UL", screenRules);
    }

     static void addUL_RecipientScreen(List<ScreenRule> rules){
        List<ScreenRule> screenRules = new ArrayList<>(rules);
        ScreenRule screenRule = new ScreenRule("s_AuthorityDocument_Employee");
        screenRule.protectedField("equals", "false");
        ScreenRule screenRule1 = new ScreenRule("");
        screenRule1.protectedField("equals", "true");
        screenRules.add(screenRule);
        serviceInit.put("s_UL_RecipientEmployeeEmailData", screenRules);
    }
}
