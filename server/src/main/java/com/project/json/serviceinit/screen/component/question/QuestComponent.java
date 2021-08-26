package com.project.json.serviceinit.screen.component.question;

import com.project.json.dto.FieldDto;
import com.project.json.serviceinit.screen.component.AbstractComponent;
import lombok.Data;

import java.util.List;

@Data
public class QuestComponent extends AbstractComponent {

    QuestAttr attrs;

    public QuestComponent(String id, List<FieldDto> fieldDtos) {
        this.id = id;
        this.type = "QuestionScr";
        attrs = new QuestAttr(fieldDtos);
    }
}
