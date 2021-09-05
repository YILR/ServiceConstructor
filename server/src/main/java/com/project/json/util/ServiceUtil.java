package com.project.json.util;

import com.project.json.dto.*;
import com.project.json.facade.Facade;
import com.project.json.serviceinit.ServiceInit;
import com.project.json.serviceinit.screen.component.AbstractComponent;
import com.project.json.serviceinit.screen.rule.Condition;
import com.project.json.serviceinit.screen.rule.cyclescreenrule.CycleScreenRule;
import com.project.json.serviceinit.screen.rule.screenrule.ScreenRule;


import java.util.*;
import java.util.stream.Collectors;

public class ServiceUtil {

    public static void initService(ServiceInit serviceInit, List<ScreenDto> screenDtos) {
        screenDtos.forEach(serviceInit::addScreen);
    }

    public static List<String> mapIdComponents(List<ComponentDto> componentDtos) {
        List<String> idComp = new ArrayList<>();
        componentDtos.forEach(componentDto -> idComp.add(componentDto.getId()));
        return idComp;
    }

    public static List<ScreenRule> mapScreenRule(List<ScreenRuleDto> screenRuleDtos) {

        return screenRuleDtos.stream().map(scrD -> {
            ScreenRule sc = new ScreenRule(scrD.getNextDisplay());
            sc.getConditions().addAll(mapConditions(scrD));
            return sc;
        }).collect(Collectors.toList());
    }

    public static List<CycleScreenRule> mapCycleScreenRule(List<CycleScreenRuleDto> cycleScreenRuleDtos){
        return cycleScreenRuleDtos.stream().map(cycleScrR -> {
            CycleScreenRule cSR = new CycleScreenRule(cycleScrR.getNextDisplay());
            cSR.getConditions().addAll(mapConditions(cycleScrR));
            return cSR;
        }).collect(Collectors.toList());
    }

    public static void addComponentsToFields(Set<AbstractComponent> applicationFields, List<ComponentDto> componentDtos) {
        for (ComponentDto cp : componentDtos) {
            if ("QuestionScr".equals(cp.getType())) {
                applicationFields.add(Facade.questFacade(cp));
            } else if ("StringInput".equals(cp.getType()) || "SnilsInput".equals(cp.getType())) {
                applicationFields.add(Facade.stringInputFacade(cp));
            } else if ("DateInput".equals(cp.getType())) {
                applicationFields.add(Facade.dateInputFacade(cp));
            } else if ("AddressInput".equals(cp.getType())) {
                applicationFields.add(Facade.addressInputFacade(cp));
            } else if ("CheckBox".equals(cp.getType())) {
                applicationFields.add(Facade.checkBoxFacade(cp));
            } else if ("Lookup".equals(cp.getType())) {
                applicationFields.add(Facade.lookupFacade(cp));
            } else if ("FileUploadComponent".equals(cp.getType())) {
                applicationFields.add(Facade.fileUploadComponentFacade(cp));
            } else if ("TextArea".equals(cp.getType())) {
                applicationFields.add(Facade.textAreaFacade(cp));
            } else if ("RadioInput".equals(cp.getType())) {
                applicationFields.add(Facade.radioInputFacade(cp));
            } else if ("DropDown".equals(cp.getType())) {
                applicationFields.add(Facade.dropDownFacade(cp));
            } else if("LabelSection".equals(cp.getType())){
                applicationFields.add(Facade.labelSectionFacade(cp));
            }
        }
    }

    private static List<Condition> mapConditions(RuleDto scrD) {
        return scrD.getConditions().stream().map(cond -> {
            if (Objects.isNull(cond.getPredicate()) || cond.getPredicate().equals("null"))
                return (new Condition(cond.getField(), cond.getValue()));

            return (new Condition(cond.getField(), cond.getValue(), cond.getPredicate()));

        }).collect(Collectors.toList());
    }

}
