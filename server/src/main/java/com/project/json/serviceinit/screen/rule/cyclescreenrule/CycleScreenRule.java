package com.project.json.serviceinit.screen.rule.cyclescreenrule;

import com.project.json.serviceinit.screen.rule.Condition;
import com.project.json.serviceinit.screen.rule.Rule;
import lombok.Data;

@Data
public class CycleScreenRule extends Rule {

    public CycleScreenRule(String nextDisplay) {
        this.nextDisplay = nextDisplay;
    }

    public CycleScreenRule(String field, String nextDisplay) {
        conditions.add(new Condition(field));
        this.nextDisplay = nextDisplay;
    }

    public CycleScreenRule(String field, String value, String nextDisplay) {
        conditions.add(new Condition(field, value));
        this.nextDisplay = nextDisplay;
    }
}
