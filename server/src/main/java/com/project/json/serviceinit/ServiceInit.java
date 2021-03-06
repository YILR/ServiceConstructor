package com.project.json.serviceinit;

import com.fasterxml.jackson.annotation.JsonAnySetter;
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
public class ServiceInit {
    private String service;
    private String init = "ServiceRounting";
    private int draftTtl = 90;
    private boolean saveDraftModeEnabled = true;
    private List<AbstractScreen> screens = new ArrayList<>();
    private Set<AbstractComponent> applicationFields = new LinkedHashSet<>();
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

        screens.add(new Screen("s_main_error", "INFO", "???????????? ???????????????????? ?? ?????????? ??????????????", "?????????????? ???? ??????????????", "MainInfo_error"));
        applicationFields.add(new InfoComponent("MainInfo_error", "<p align='justify'><b>????????????????!</b><br/>?????? ????????????: ${region}. ???????????? ?? ?????? ????????????????????.</p>"));
        put("s_main_error", Collections.singletonList(new ScreenRule("MainInfo_error", "RedirectScreenPersonalPage_error")));

        Screen screen = new Screen("RedirectScreenPersonalPage_error", "EMPTY", "", null, "redirectcomponentpp_error");
        screen.hasTerminal();
        screens.add(screen);
        applicationFields.add(new Redirect("redirectcomponentpp_error", "https://www.gosuslugi.ru"));
    }

    public void initMain(ScreenDto screenDto) {
        service = screenDto.getHeader();
        screens.add(new Screen(true, "s_main", "INFO", screenDto.getHeader(), "????????????", "MainInfo"));
        applicationFields.add(new InfoComponent("MainInfo"));
        String nextDisplay = screenDto.getScreenRules().get(0).getNextDisplay().isEmpty() ? "s_Goal" : screenDto.getScreenRules().get(0).getNextDisplay();
        put("s_main", Collections.singletonList(new ScreenRule("MainInfo", nextDisplay)));
    }

    public void initEnd(int code) {
        screens.add(new Screen("s_CompetentOrganization", "UNIQUE", "?????????? ?????????????????????????????? ????????????, ???????????????????????????????? ????????????", "??????????", "Organization"));
        applicationFields.add(new MapService("Organization", "<p>???????????????? ??????????????????????????</p>", code));
        put("s_CompetentOrganization", Collections.singletonList(new ScreenRule("Organization", "s_MethodGettingResults")));

        List<ComponentDto> componentDtos = new ArrayList<>();
        componentDtos.add(new ComponentDto("MethodGettingResults_Info", "LabelSection", "<p>?????????????????????? ?????????????????? ???????????????????????????? ???????????? ?????????? ???????????????????????? ?? ?????? ???????????? ?????????????? ???? ????????.</p>"));
        componentDtos.add(new ComponentDto("IsPaperDocumentRequired", "CheckBox", "???????????????? ?????????????????????????? ?????????????????? ???? ???????????????? ????????????????"));
        componentDtos.add(new ComponentDto("MethodGettingResults_Choose", "RadioInput", "", Arrays.asList(new FieldDto("??????", "1"), new FieldDto("???????????? ?????????????????? ?? ???????????????????????????? ??????????", "2")), Collections.singletonList(new RefDto("IsPaperDocumentRequired", "displayOn", "true")), "isVertical", true));
        screens.add(new Screen("s_MethodGettingResults", "CUSTOM", "???????????? ?????????????????? ????????????????????", "???????????? ??????????????????", ServiceUtil.mapIdComponents(componentDtos)));
        ServiceUtil.addComponentsToFields(applicationFields, componentDtos);
        put("s_MethodGettingResults", Collections.singletonList(new ScreenRule("IsPaperDocumentRequired", "RedirectScreenPersonalPage")));

        Screen redirect = new Screen("RedirectScreenPersonalPage", "EMPTY", "", null, "RedirectComponentpp");
        redirect.setIsTerminal(true);
        screens.add(redirect);
        applicationFields.add(new Redirect("RedirectComponentpp", "?????????????? ?? ???????????? ??????????????", "??????????????????????"));
    }

    public void addScreen(ScreenDto screenDto) {

        if ("REPEATABLE".equals(screenDto.getType())) {
            String idComp = screenDto.getId().substring(2) + "Block";
            screens.add(new Screen(screenDto.getId(), screenDto.getType(), screenDto.getHeader(), "??????????", idComp, screenDto.getSuggestion()));
            applicationFields.add(new RepeatableField(idComp, ServiceUtil.mapIdComponents(screenDto.getComponents())));
        } else if ("QUESTION".equals(screenDto.getType())) {
            screens.add(new Screen(screenDto.getId(), screenDto.getType(), screenDto.getHeader(), null, ServiceUtil.mapIdComponents(screenDto.getComponents())));
        } else if ("C_UNIQUE".equals(screenDto.getType())) {
            screens.add(new Screen(screenDto.getId(), "UNIQUE", screenDto.getHeader(), "??????????", screenDto.getChild().getId(), screenDto.getSuggestion()));
            ChildDto childDto = screenDto.getChild();
            applicationFields.add(new ChildrenList(childDto.getId(), childDto.getLabel(), childDto.getSingleChild(), childDto.getIsCycled(), childDto.getMinAge(), childDto.getMaxAge(), ServiceUtil.mapIdComponents(screenDto.getComponents())));
        } else {
            screens.add(new Screen(screenDto.getId(), screenDto.getType(), screenDto.getHeader(), "??????????", ServiceUtil.mapIdComponents(screenDto.getComponents()), screenDto.getSuggestion()));
        }
        ServiceUtil.addComponentsToFields(applicationFields, screenDto.getComponents());
        if (screenDto.getScreenRules() != null)
            put(screenDto.getId(), ServiceUtil.mapScreenRule(screenDto.getScreenRules()));
        if (screenDto.getCycleScreenRules() != null)
            putCycle(screenDto.getId(), ServiceUtil.mapCycleScreenRule(screenDto.getCycleScreenRules()));
    }

    public void addScreen(String id, String header, String idCompon, String typeComp, String labelComp, String addrType, String nextDisplay) {
        screens.add(new Screen(id, "UNIQUE", header, "??????????", idCompon));
        applicationFields.add(new ConfirmData(idCompon, typeComp, labelComp, addrType));
        put(id, Collections.singletonList(new ScreenRule(idCompon, nextDisplay)));
    }

    public void addScreen(String id, String header, String idCompon, String typeComp, String labelComp, String addrType, String... nextDisplay) {
        screens.add(new Screen(id, "UNIQUE", header, "??????????", idCompon));
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
