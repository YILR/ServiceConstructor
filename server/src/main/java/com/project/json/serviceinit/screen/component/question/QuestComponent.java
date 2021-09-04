package com.project.json.serviceinit.screen.component.question;

import com.project.json.dto.FieldDto;
import com.project.json.serviceinit.screen.component.AbstractComponent;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuestComponent extends AbstractComponent {

    private QuestAttr attrs;

    public QuestComponent(String id, String type, List<FieldDto> fieldDtos) {
        super(id, type, "");
        attrs = new QuestAttr(fieldDtos);
    }
}
