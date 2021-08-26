package com.project.json.serviceinit.screen.component.mapservice;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
public class AttrsMap {

    private Boolean autoCenterAllPoints = true;
    private Boolean validationOn = false;
    private String attributeNameWithAddress = "ADDRESS";
    private String dictionaryType = "PGS_CNSTR_ORG";
    private List<DictionaryFilter> dictionaryFilter = new ArrayList<>();
    private String[] selectAttributes = {"ADDRESS", "SHORT_TITLE"};
    private List<BaloonContent> baloonContent = Arrays.asList(new BaloonContent("ADDRESS", "Адрес"), new BaloonContent("SHORT_TITLE", "Наименование"));
    private AddressString addressString = new AddressString();
    private List<String> actions = new ArrayList<>();
    private List<String> fields = new ArrayList<>();
    private String value = "";
    private Boolean visited = false;

    public AttrsMap(int code) {
        String value = "{\"asString\":\"-10000000"+ code +"\"}";
        dictionaryFilter.add(new DictionaryFilter("SERVICE_CODE", value, "value"));
        dictionaryFilter.add(new DictionaryFilter("REGOKATO", "infSysCode", "serviceInfo"));
    }
}
