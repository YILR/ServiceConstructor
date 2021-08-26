package com.project.json.serviceinit.screen.component.fileupload;

import com.project.json.serviceinit.screen.component.AbstractComponent;
import lombok.Data;

@Data
public class FileUploadComponent extends AbstractComponent {

    private Boolean required;
    private AttrsFile attrs = new AttrsFile();

    public FileUploadComponent(String id, String label, String suggestionId, String[] fileType, Integer maxSize, Boolean required) {
        this.id = id;
        this.type = "FileUploadComponent";
        this.suggestionId = suggestionId;
        this.required = required;
        String uploadId = id.substring(2);
        maxSize *= 1048576;
        attrs.getUploads().add(new Upload(uploadId, label, fileType, maxSize));
    }
}
