package ru.spb.vika.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OperationMinDataResponse {

    private Integer id;

    @NotBlank(message = "Operation name must not be empty")
    private String opsName;
}
