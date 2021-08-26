package com.project.json.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


public class CycleScreenRuleDto extends RuleDto{

    public CycleScreenRuleDto() {
    }

    public CycleScreenRuleDto(String nextDisplay, List<ConditionDto> conditions) {
        super(nextDisplay, conditions);
    }
}
