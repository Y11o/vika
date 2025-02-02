package ru.spb.vika.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServerMediaResponse {

    private Integer taskID;

    private Integer uploadedFileId;

    private String name;

    private String content;
}
