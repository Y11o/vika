package ru.spb.vika.dto.OperationDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ConditionDTO {

    private Integer id;

    @NotBlank(message = "Condition name must not be empty")
    private String condition;

    private String description;

    private boolean passed;
}
