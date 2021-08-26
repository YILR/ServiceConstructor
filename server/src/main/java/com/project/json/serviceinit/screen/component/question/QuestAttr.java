package com.project.json.serviceinit.screen.component.question;

import com.project.json.dto.FieldDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class QuestAttr {

    private List<Answers> answers = new ArrayList<>();

    public QuestAttr(List<FieldDto> fieldDtos) {
        answers.addAll(fieldDtos.stream()
                .map(fieldDto ->new Answers(fieldDto.getLabel(), fieldDto.getValue()))
                .collect(Collectors.toList()));
    }

}
