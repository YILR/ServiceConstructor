package com.project.json.dto;

import java.util.List;


public class ScreenRuleDto extends RuleDto{

    public ScreenRuleDto() {
    }

    public ScreenRuleDto(String nextDisplay, List<ConditionDto> conditions) {
        super(nextDisplay, conditions);
    }
}
