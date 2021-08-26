package com.project.json.util;

import com.project.json.dto.ScreenDto;
import com.project.json.serviceinit.ServiceInit;
import com.project.json.util.roleapplicant.BusinessUtil;
import com.project.json.util.roleapplicant.LegalUtil;
import com.project.json.util.roleapplicant.PersonUtil;

import java.util.ArrayList;
import java.util.List;

public class ApplicantUtil {

    public static void initPersons(ServiceInit serviceInit, List<ScreenDto> screenDtos) {
        List<ScreenDto> removeScreens = new ArrayList<>();
        for (ScreenDto s : screenDtos) {
            if (s.getId().equals("s_main")) {
                serviceInit.initMain(s);
                removeScreens.add(s);
            } else if (s.getId().equals("s_FL_RecipientData")) {
                PersonUtil.initFl(serviceInit, ServiceUtil.mapScreenRule(s.getScreenRules()));
                removeScreens.add(s);
            } else if (s.getId().equals("s_IP_RecipientData")) {
                BusinessUtil.initIp(serviceInit, ServiceUtil.mapScreenRule(s.getScreenRules()));
                removeScreens.add(s);
            } else if (s.getId().equals("s_UL_RecipientData")) {
                LegalUtil.initUl(serviceInit, ServiceUtil.mapScreenRule(s.getScreenRules()));
                removeScreens.add(s);
            } else if (s.getId().equals("s_FL_RepresentativeData")) {
                PersonUtil.representativeFl(serviceInit, ServiceUtil.mapScreenRule(s.getScreenRules()));
                removeScreens.add(s);
            } else if (s.getId().equals("s_IP_RepresentativeData")) {
                BusinessUtil.representativeIp(serviceInit, ServiceUtil.mapScreenRule(s.getScreenRules()));
                removeScreens.add(s);
            } else if (s.getId().equals("s_UL_RepresentativeData")) {
                LegalUtil.representativeUl(serviceInit, ServiceUtil.mapScreenRule(s.getScreenRules()));
                removeScreens.add(s);
            } else if (s.getId().equals("s_FL_RecipientDataManual")) {
                PersonUtil.applicantFl(serviceInit, ServiceUtil.mapScreenRule(s.getScreenRules()));
                removeScreens.add(s);
            } else if (s.getId().equals("s_IP_RecipientDataManual")) {
                BusinessUtil.applicantIp(serviceInit, ServiceUtil.mapScreenRule(s.getScreenRules()));
                removeScreens.add(s);
            } else if (s.getId().equals("s_UL_RecipientDataManual")) {
                LegalUtil.applicantUl(serviceInit, ServiceUtil.mapScreenRule(s.getScreenRules()));
                removeScreens.add(s);
            }
        }
        screenDtos.removeAll(removeScreens);
    }
}
