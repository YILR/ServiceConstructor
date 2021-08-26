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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BusinessUtil {

    public static void initIp(ServiceInit serviceInit, List<ScreenRule> screenRules) {
        serviceInit.addScreen("s_IP_RecipientData", "Сведения о заявителе", "RecipientBusinessmanData", "ConfirmLegalData", "", null, "s_IP_RecipientPhoneData");
        serviceInit.addScreen("s_IP_RecipientPhoneData", "Контактный телефон заявителя", "RecipientPhoneData", "ConfirmPersonalUserPhone", "Контактный телефон", null, "s_IP_RecipientEmailData");
        serviceInit.addScreen("s_IP_RecipientEmailData", "Электронная почта заявителя", "RecipientEmailData", "ConfirmPersonalUserEmail", "Электронная почта", null, "s_IP_RecipientRegAddressData");
        serviceInit.addScreen("s_IP_RecipientRegAddressData", "Адрес регистрации заявителя", "RecipientRegAddressData", "ConfirmPersonalUserRegAddr", "", "permanentRegistry", "");
        serviceInit.put("s_IP_RecipientRegAddressData", screenRules);
    }

    public static void representativeIp(ServiceInit serviceInit, List<ScreenRule> screenRules) {
        serviceInit.addScreen("s_IP_RepresentativeData", "Сведения о представителе", "RepresentativeBusinessmanData", "ConfirmLegalData", "", null, "s_IP_RepresentativePhoneData");
        serviceInit.addScreen("s_IP_RepresentativePhoneData", "Контактный телефон представителя", "RepresentativePhoneData", "ConfirmPersonalUserPhone", "Контактный телефон", null, "s_IP_RepresentativeEmailData");
        serviceInit.addScreen("s_IP_RepresentativeEmailData", "Электронная почта представителя", "RepresentativeEmailData", "ConfirmPersonalUserEmail", "Электронная почта", null, "s_IP_RepresentativeRegAddressData");
        serviceInit.addScreen("s_IP_RepresentativeRegAddressData", "Адрес регистрации представителя", "RepresentativeRegAddressData", "ConfirmPersonalUserRegAddr", "", null, "s_IP_RecipientDataManual", "s_UL_RecipientDataManual", "s_FL_RecipientDataManual");
        if(screenRules != null)
            serviceInit.put("s_IP_RepresentativeRegAddressData", screenRules);
    }

    public static void applicantIp(ServiceInit serviceInit, List<ScreenRule> screenRules) {
        List<AbstractComponent> applicationFields = serviceInit.getApplicationFields();
        serviceInit.getScreens().add(new Screen("s_IP_RecipientDataManual", "CUSTOM", "Сведения о заявителе", "Далее", Arrays.asList("FullnameIP", "OGRNIP", "INNIP"), "legal_data"));
        applicationFields.add(new TextArea("FullnameIP", "Полное наименование", "2000", "org_full_name",true));
        applicationFields.add(new OtherInput("OGRNIP", "OgrnipInput", "ОГРНИП", "org_ogrn",new ValidOther(null, "Указан некорректный ОГРНИП"),  new String[]{"/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/"}));
        applicationFields.add(new OtherInput("INNIP", "PersonInnInput", "ИНН", "org_inn",new ValidOther(null, "Указан некорректный ИНН"),  new String[]{"/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/", "/\\d/"}));
        serviceInit.put("s_IP_RecipientDataManual", Collections.singletonList(new ScreenRule("FullnameIP", "s_AuthorityDocument_IP")));

        serviceInit.getScreens().add(new Screen("s_AuthorityDocument_IP", "UNIQUE", "Загрузите документы", "Далее", "f_AuthorityDocument_IP"));
        ServiceUtil.addComponentsToFields(applicationFields, Collections.singletonList(new ComponentDto("f_AuthorityDocument_IP", "FileUploadComponent", "Документ, подтверждающий полномочия представителя на подачу заявления от имени индивидуального предпринимателя", "Необходимо загрузить: <br/>1. Документ, подтверждающий полномочия представителя действовать от имени заявителя, подписанный усиленной квалифицированной электронной подписью заявителя или нотариуса.<br/> 2. Файл с открепленной усиленной квалифицированной электронной подписью заявителя или нотариуса в формате SIG.",
                "prev_files_power_att", new String[]{"PDF", "XML", "ZIP", "RAR", "SIG"}, 50, true)));
        serviceInit.put("s_AuthorityDocument_IP", screenRules);
    }

}
