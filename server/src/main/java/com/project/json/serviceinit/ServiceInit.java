package com.project.json.serviceinit;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.json.dto.*;
import com.project.json.serviceinit.screen.AbstractScreen;
import com.project.json.serviceinit.screen.Screen;
import com.project.json.serviceinit.screen.component.AbstractComponent;
import com.project.json.serviceinit.screen.component.childrenlist.ChildrenList;
import com.project.json.serviceinit.screen.component.confirm.ConfirmData;
import com.project.json.serviceinit.screen.component.infoscr.InfoComponent;
import com.project.json.serviceinit.screen.component.mapservice.MapService;
import com.project.json.serviceinit.screen.component.redirect.Redirect;
import com.project.json.serviceinit.screen.component.repeatable.RepeatableField;
import com.project.json.serviceinit.screen.component.restcall.RestCall;
import com.project.json.serviceinit.screen.rule.cyclescreenrule.CycleScreenRule;
import com.project.json.serviceinit.screen.rule.screenrule.ScreenRule;
import com.project.json.util.ServiceUtil;
import lombok.Data;

import java.util.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceInit {
    private String service;
    private String init = "ServiceRounting";
    private int draftTtl = 90;
    private boolean saveDraftModeEnabled = true;
    private List<AbstractScreen> screens = new ArrayList<>();
    private List<AbstractComponent> applicationFields = new ArrayList<>();
    private Map<String, List<ScreenRule>> screenRules = new LinkedHashMap<>();
    private Map<String, List<CycleScreenRule>> cycledScreenRules;

    public ServiceInit(int code) {
        init(code);
    }

    public void init(int code) {
        Screen routing = new Screen("ServiceRounting", "EMPTY", "", null, "ServiceRountingRequest");
        routing.logic();
        screens.add(routing);
        applicationFields.add(new RestCall("CheckRegion", "RestCall", code));
        applicationFields.add(new Redirect("ServiceRountingRequest"));
        ScreenRule screenRule1 = new ScreenRule("s_main");
        screenRule1.field("CheckRegion.value.code", "200", "equals");
        ScreenRule screenRule2 = new ScreenRule("s_main_error");
        screenRule2.field("CheckRegion.value.code", "404", "equals");
        put("ServiceRounting", Arrays.asList(screenRule1, screenRule2));

        screens.add(new Screen("s_main_error", "INFO", "Услуга недоступна в вашем регионе", "Перейти на главную", "MainInfo_error"));
        applicationFields.add(new InfoComponent("MainInfo_error", "<p align='justify'><b>Внимание!</b><br/>Ваш регион: ${region}. Услуга в нём недоступна.</p>"));
        put("s_main_error", Collections.singletonList(new ScreenRule("MainInfo_error", "RedirectScreenPersonalPage_error")));

        Screen screen = new Screen("RedirectScreenPersonalPage_error", "EMPTY", "", null, "redirectcomponentpp_error");
        screen.hasTerminal();
        screens.add(screen);
        applicationFields.add(new Redirect("redirectcomponentpp_error", "https://www.gosuslugi.ru"));
    }

    public void initMain(ScreenDto screenDto) {
        service = screenDto.getHeader();
        screens.add(new Screen(true, "s_main", "INFO", screenDto.getHeader(), "Начать", "MainInfo"));
        applicationFields.add(new InfoComponent("MainInfo"));
        String nextDisplay = screenDto.getScreenRules().get(0).getNextDisplay().isEmpty() ? "s_Goal" : screenDto.getScreenRules().get(0).getNextDisplay();
        put("s_main", Collections.singletonList(new ScreenRule("MainInfo", nextDisplay)));
    }

    public void initEnd(int code) {
        screens.add(new Screen("s_CompetentOrganization", "UNIQUE", "Выбор уполномоченного органа, предоставляющего услугу", "Далее", "Organization"));
        applicationFields.add(new MapService("Organization", "<p>Выберите подразделение</p>", code));
        put("s_CompetentOrganization", Collections.singletonList(new ScreenRule("Organization", "s_MethodGettingResults")));
        List<ComponentDto> componentDtos = new ArrayList<>();
        componentDtos.add(new ComponentDto("MethodGettingResults_Info", "LabelSection", "<p>Электронный результат предоставления услуги будет предоставлен в Ваш личный кабинет на ЕПГУ.</p>"));
        componentDtos.add(new ComponentDto("IsPaperDocumentRequired", "CheckBox", "Получить дополнительно результат на бумажном носителе"));
        componentDtos.add(new ComponentDto("MethodGettingResults_Choose", "RadioInput", "", Arrays.asList(new FieldDto("МФЦ", "1"), new FieldDto("Личное обращение в уполномоченный орган", "2")), Collections.singletonList(new RefDto("IsPaperDocumentRequired", "displayOn", "true")), "isVertical", true));
        screens.add(new Screen("s_MethodGettingResults", "CUSTOM", "Способ получения результата", "Подать заявление", ServiceUtil.mapIdComponents(componentDtos)));
        ServiceUtil.addComponentsToFields(applicationFields, componentDtos);
        put("s_MethodGettingResults", Collections.singletonList(new ScreenRule("IsPaperDocumentRequired", "RedirectScreenPersonalPage")));
        Screen redirect = new Screen("RedirectScreenPersonalPage", "EMPTY", "", null, "RedirectComponentpp");
        redirect.setIsTerminal(true);
        screens.add(redirect);
        applicationFields.add(new Redirect("RedirectComponentpp", "Перейти в личный кабинет", "Подтвердить"));
    }

    public void addScreen(ScreenDto screenDto) {

        if (screenDto.getType().equals("REPEATABLE")) {
            String idComp = screenDto.getId().substring(2) + "Block";
            screens.add(new Screen(screenDto.getId(), screenDto.getType(), screenDto.getHeader(), "Далее", idComp));
            if (applicationFields.stream().noneMatch(abstractComponent -> abstractComponent.getId().equals(idComp))) {
                applicationFields.add(new RepeatableField(idComp, ServiceUtil.mapIdComponents(screenDto.getComponents())));
            }
        } else if (screenDto.getType().equals("QUESTION")) {
            screens.add(new Screen(screenDto.getId(), screenDto.getType(), screenDto.getHeader(), null, ServiceUtil.mapIdComponents(screenDto.getComponents())));
        } else if (screenDto.getType().equals("CUNIQUE")) {
            screens.add(new Screen(screenDto.getId(), "UNIQUE", screenDto.getHeader(), "Далее", screenDto.getChild().getId()));
            ChildDto childDto = screenDto.getChild();
            if (applicationFields.stream().noneMatch(abstractComponent -> abstractComponent.getId().equals(childDto.getId()))) {
                applicationFields.add(new ChildrenList(childDto.getId(), childDto.getLabel(), childDto.getSingleChild(), childDto.getIsCycled(), childDto.getMinAge(), childDto.getMaxAge(), ServiceUtil.mapIdComponents(screenDto.getComponents())));
            }
        } else {
            screens.add(new Screen(screenDto.getId(), screenDto.getType(), screenDto.getHeader(), "Далее", ServiceUtil.mapIdComponents(screenDto.getComponents()), screenDto.getSuggestion()));
        }
        ServiceUtil.addComponentsToFields(applicationFields, screenDto.getComponents());
        if (screenDto.getScreenRules() != null)
            put(screenDto.getId(), ServiceUtil.mapScreenRule(screenDto.getScreenRules()));
        if (screenDto.getCycleScreenRules() != null)
            putCycle(screenDto.getId(), ServiceUtil.mapCycleScreenRule(screenDto.getCycleScreenRules()));
    }

    public void addScreen(String id, String header, String idCompon, String typeComp, String labelComp, String addrType, String nextDisplay) {
        screens.add(new Screen(id, "UNIQUE", header, "Верно", idCompon));
        if (applicationFields.stream().noneMatch(abstractComponent -> abstractComponent.getId().equals(idCompon))) {
            applicationFields.add(new ConfirmData(idCompon, typeComp, labelComp, addrType));
        }
        put(id, Collections.singletonList(new ScreenRule(idCompon, nextDisplay)));
    }

    public void addScreen(String id, String header, String idCompon, String typeComp, String labelComp, String addrType, String... nextDisplay) {
        screens.add(new Screen(id, "UNIQUE", header, "Верно", idCompon));
        if (applicationFields.stream().noneMatch(abstractComponent -> abstractComponent.getId().equals(idCompon)))
            applicationFields.add(new ConfirmData(idCompon, typeComp, labelComp, addrType));
        put(id, Arrays.asList(new ScreenRule("q_KP_OA_02", "BUSINESS", nextDisplay[0]), new ScreenRule("q_KP_OA_02", "LEGAL", nextDisplay[1]), new ScreenRule("q_KP_OA_02", "PERSON_RF", nextDisplay[2])));
    }


    @JsonAnySetter
    public void put(String key, List<ScreenRule> value) {
        screenRules.put(key, value);
    }

    @JsonAnySetter
    public void putCycle(String key, List<CycleScreenRule> value) {
        if (cycledScreenRules == null) {
            cycledScreenRules = new LinkedHashMap<>();
        }
        cycledScreenRules.put(key, value);
    }

}
