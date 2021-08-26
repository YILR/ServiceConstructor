package com.project.json.facade;

import com.project.json.dto.ComponentDto;
import com.project.json.serviceinit.screen.component.adressinput.AddressInput;
import com.project.json.serviceinit.screen.component.checkbox.CheckBox;
import com.project.json.serviceinit.screen.component.dateinput.DateInput;
import com.project.json.serviceinit.screen.component.dropdown.DropDown;
import com.project.json.serviceinit.screen.component.fileupload.FileUploadComponent;
import com.project.json.serviceinit.screen.component.labelsection.LabelSection;
import com.project.json.serviceinit.screen.component.lookup.Lookup;
import com.project.json.serviceinit.screen.component.question.QuestComponent;
import com.project.json.serviceinit.screen.component.radioinput.RadioInput;
import com.project.json.serviceinit.screen.component.stringinput.StringInput;
import com.project.json.serviceinit.screen.component.textarea.TextArea;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Facade {

    public static QuestComponent questFacade(ComponentDto cp){
        return new QuestComponent(cp.getId(), cp.getField());
    }

    public static StringInput stringInputFacade(ComponentDto cp){
        StringInput stringInput = new StringInput(cp.getId(), cp.getLabel(), cp.getHint(), cp.getField(), cp.getMask(), cp.getSuggestionId(), cp.getRequired());
        if(stringInput.getId().matches("(.*)[Ll]ast[Nn]ame(.*)") || stringInput.getId().matches("(.*)[Ff]irst[Nn]ame(.*)") || stringInput.getId().matches("(.*)[Mm]iddle[Nn]ame(.*)")){
            stringInput.getAttrs().fstuc();
        }
        stringInput.getAttrs().setFields(cp.getFieldName());
        if(cp.getRef() != null) {
            stringInput.getAttrs().putRef(cp.getRef());
        }
        if(cp.getGrid()){
            stringInput.getAttrs().grid();
        }
        stringInput.getAttrs().setHidden(cp.getHidden());
        return stringInput;
    }

    public static DateInput dateInputFacade(ComponentDto cp){
        DateInput dateInput = new DateInput(cp.getId(), cp.getLabel(), cp.getSuggestionId(),cp.getRequired());
        dateInput.getAttrs().setFields(cp.getFieldName());
        if(cp.getRef() != null){
            dateInput.getAttrs().putRef(cp.getRef());
        }
        return dateInput;
    }

    public static AddressInput addressInputFacade(ComponentDto cp){
        return new AddressInput(cp.getId(), cp.getLabel(), cp.getRequired());
    }

    public static CheckBox checkBoxFacade(ComponentDto cp){
        CheckBox checkBox =  new CheckBox(cp.getId(), cp.getLabel());
        if(cp.getAtLeastOne())
            checkBox.getAttrs().validation();
        return checkBox;
    }

    public static Lookup lookupFacade(ComponentDto cp){
        return new Lookup(cp.getId(), cp.getLabel(), cp.getDictionaryType());
    }

    public static FileUploadComponent fileUploadComponentFacade(ComponentDto cp){
        StringBuilder stringBuilder = builderLabel(cp);
        return new FileUploadComponent(cp.getId(), stringBuilder.toString(), cp.getSuggestionId(), mapToString(cp.getFileType()), cp.getMaxSize(), cp.getRequired());
    }

    public static TextArea textAreaFacade(ComponentDto cp){
        return new TextArea(cp.getId(), cp.getLabel(), cp.getSuggestionId(), cp.getCharsAmount(), cp.getRequired());
    }

    public static RadioInput radioInputFacade(ComponentDto cp){
        RadioInput radioInput = new RadioInput(cp.getId(), cp.getLabel(), cp.getRequired(), cp.getFieldName(), cp.getField(), cp.getHidden());
        if(cp.getPosition().equals("isHorizontal"))
            radioInput.getAttrs().setIsHorizontal(true);
        else radioInput.getAttrs().setIsVertical(true);

        if(cp.getRef() != null){
            radioInput.getAttrs().putRef(cp.getRef());
        }
        return radioInput;
    }

    public static DropDown dropDownFacade(ComponentDto cp){
        return new DropDown(cp.getId(), cp.getLabel(), cp.getField(), cp.getRequired());
    }

    public static LabelSection labelSectionFacade(ComponentDto cp){
        return new LabelSection(cp.getId(), cp.getLabel());
    }

    private static StringBuilder builderLabel(ComponentDto cp) {
        StringBuilder stringBuilder = new StringBuilder("<h5 align='justify'>");
        stringBuilder.append(cp.getLabel());
        stringBuilder.append("</h5>");
        String fileType = Arrays.stream(mapToString(cp.getFileType())).map(f -> "*."+f).collect(Collectors.joining(", "));
        if(cp.getHint() != null){
            stringBuilder.append("<p align='justify' style='font-size:18px'>");
            stringBuilder.append(cp.getHint());
            stringBuilder.append("</p>");
        }
        String upload = "<p align='justify' style='font-size:18px'>Для загрузки выберите файл с расширением "+ fileType +".<br>Максимально допустимый размер файла — " + cp.getMaxSize() + " Мб.</p>";
        stringBuilder.append(upload);
        return stringBuilder;
    }

    private static String[] mapToString(Map<String, Boolean> fieldTypes){

        return fieldTypes.entrySet().stream().filter(Map.Entry::getValue).map(Map.Entry::getKey).toArray(String[]::new);
    }

}
