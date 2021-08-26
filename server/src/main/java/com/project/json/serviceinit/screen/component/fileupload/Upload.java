package com.project.json.serviceinit.screen.component.fileupload;

import lombok.Data;

@Data
public class Upload {

    private String uploadId;
    private String type = "single";
    private String label;
    private String[] fileType;
    private Integer maxSize;
    private Integer maxFileCount = 10;

    public Upload(String uploadId, String label, String[] fileType, Integer maxSize) {
        this.uploadId = uploadId;
        this.label = label;
        this.fileType = fileType;
        this.maxSize = maxSize;
    }
}
