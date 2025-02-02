package ru.spb.vika.dto;

import lombok.Data;

@Data
public class ServerMediaDTO {

    private Integer taskID;

    private  UploadedFileDTO mediafile;
}
