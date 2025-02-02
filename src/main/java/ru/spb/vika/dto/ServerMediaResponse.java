package ru.spb.vika.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServerMediaResponse {

    private Integer uploadedFileId;

    private Integer taskID;

    private String name;

    private String content;
}
