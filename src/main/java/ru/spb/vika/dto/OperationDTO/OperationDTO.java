package ru.spb.vika.dto.OperationDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OperationDTO {

    private Integer id;

    @NotBlank(message = "Operation name must not be empty")
    private String name;

    @NotBlank(message = "Operation must not be empty")
    private String operation;
}
