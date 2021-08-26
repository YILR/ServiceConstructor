package com.project.json.serviceinit.screen.component.fileupload;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AttrsFile {

    private List<Upload> uploads = new ArrayList<>();

    public AttrsFile() {

    }
}
