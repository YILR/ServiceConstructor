package com.project.json.serviceinit.screen.rule.screenrule;

import com.project.json.serviceinit.screen.rule.Condition;
import com.project.json.serviceinit.screen.rule.Rule;
import lombok.Data;

@Data
public class ScreenRule extends Rule {

    public ScreenRule(String nextDisplay) {
        this.nextDisplay = nextDisplay;
    }

    public ScreenRule(String field, String nextDisplay) {
        conditions.add(new Condition(field));
        this.nextDisplay = nextDisplay;
    }

    public ScreenRule(String field, String value, String nextDisplay) {
        conditions.add(new Condition(field, value));
        this.nextDisplay = nextDisplay;
    }


}
