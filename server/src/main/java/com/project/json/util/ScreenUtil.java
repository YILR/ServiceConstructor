package com.project.json.util;

import com.project.json.dto.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ScreenUtil {

    private static List<ScreenDto> list = Arrays.asList(
            new ScreenDto("s_main", "INFO", "",
                    Collections.singletonList(new ComponentDto("MainInfo","InfoScr", "")),
                    Collections.singletonList(new ScreenRuleDto("s_Goal", Collections.singletonList(new ConditionDto("MainInfo", ""))))),
            new ScreenDto("s_Goal", "QUESTION", "Цель обращения",
                    Collections.singletonList(new ComponentDto("q1_Goal", "QuestionScr", "", Collections.singletonList(new FieldDto("", "")))),
                    Collections.singletonList(new ScreenRuleDto("s_KP_OA_01", Collections.singletonList(new ConditionDto("q1_Goal", ""))))),
            new ScreenDto("s_FL_RecipientData", "UNIQUE", "Сведения о заявителе",
                    Collections.singletonList(new ComponentDto("RecipientData","", "")),
                    Collections.singletonList(new ScreenRuleDto("", Collections.singletonList(new ConditionDto("", ""))))),
            new ScreenDto("s_IP_RecipientData", "UNIQUE", "Сведения о заявителе",
                    Collections.singletonList(new ComponentDto("RecipientBusinessmanData", "", "")),
                    Collections.singletonList(new ScreenRuleDto("", Collections.singletonList(new ConditionDto("", ""))))),
            new ScreenDto("s_UL_RecipientData", "UNIQUE", "Сведения о заявителе",
                    Collections.singletonList(new ComponentDto("RecipientLegalData", "", "")),
                    Collections.singletonList(new ScreenRuleDto("", Collections.singletonList(new ConditionDto("", ""))))),
            new ScreenDto("s_KP_OA_01", "QUESTION", "Кто обращается за услугой?",
                    Collections.singletonList(new ComponentDto("q_KP_OA_01", "QuestionScr", "", Arrays.asList(new FieldDto("Заявитель", "1"), new FieldDto("Представитель", "2")))),
                    Collections.singletonList(new ScreenRuleDto("", Collections.singletonList(new ConditionDto("", ""))))),
            new ScreenDto("s_KP_OA_02", "QUESTION", "Укажите категорию заявителя",
                    Collections.singletonList(new ComponentDto("q_KP_OA_02", "QuestionScr", "", Arrays.asList(new FieldDto("Физическое лицо", "PERSON_RF"), new FieldDto("Индивидуальный предприниматель", "BUSINESS"), new FieldDto("Юридическое лицо", "LEGAL")))),
                    Collections.singletonList(new ScreenRuleDto("", Collections.singletonList(new ConditionDto("", ""))))),
            new ScreenDto("s_FL_RepresentativeData", "UNIQUE", "Сведения о представителе",
                    Collections.singletonList(new ComponentDto("RepresentativeData", "", "")),
                    Collections.singletonList(new ScreenRuleDto("", Collections.singletonList(new ConditionDto("", ""))))),
            new ScreenDto("s_IP_RepresentativeData", "UNIQUE", "Сведения о представителе",
                    Collections.singletonList(new ComponentDto("RepresentativeBusinessmanData", "", "")),
                    Collections.singletonList(new ScreenRuleDto("", Collections.singletonList(new ConditionDto("", ""))))),
            new ScreenDto("s_UL_RepresentativeData", "UNIQUE", "Сведения о представителе",
                    Collections.singletonList(new ComponentDto("RepresentativeLegalData", "", "")),
                    Collections.singletonList(new ScreenRuleDto("", Collections.singletonList(new ConditionDto("", ""))))),
            new ScreenDto("s_FL_RecipientDataManual", "CUSTOM", "Сведения о заявителе",
                    Collections.singletonList(new ComponentDto("LastName", "", "")),
                    Collections.singletonList(new ScreenRuleDto("", Collections.singletonList(new ConditionDto("f_AuthorityDocument_FL", ""))))),
            new ScreenDto("s_IP_RecipientDataManual", "CUSTOM", "Сведения о заявителе",
                    Collections.singletonList(new ComponentDto("FullnameIP","", "")),
                    Collections.singletonList(new ScreenRuleDto("", Collections.singletonList(new ConditionDto("f_AuthorityDocument_IP", ""))))),
            new ScreenDto("s_UL_RecipientDataManual", "CUSTOM", "Сведения о заявителе",
                    Collections.singletonList(new ComponentDto("Fullname", "", "")),
                    Collections.singletonList(new ScreenRuleDto("", Collections.singletonList(new ConditionDto("f_AuthorityDocument_UL", ""))))));


    public static List<ScreenDto> checkScreen(List<ScreenDto> screenDtos) {
        boolean isHave = screenDtos.stream().noneMatch(screenDto -> list.contains(screenDto));
        if (isHave) {
            screenDtos.addAll(0, list);
        }
        return screenDtos;
    }


}
