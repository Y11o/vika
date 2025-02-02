package ru.spb.vika.dto;

import lombok.Data;

@Data
public class UploadedFileDTO {

    private Integer id;

    private String name;

    private String stringifiedUint8ListFile;
}
