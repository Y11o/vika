package ru.spb.vika.dto.OperationDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ActionDTO {

    private Integer id;

    @NotBlank(message = "Action name must not be empty")
    private String action;

    private String description;
}
