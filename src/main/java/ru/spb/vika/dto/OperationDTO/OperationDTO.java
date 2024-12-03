package ru.spb.vika.dto.OperationDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OperationDTO {

    private Integer id;

    @NotBlank(message = "Operation name must not be empty")
    private String opsName;

    private List<TeamDTO> teamClasses = new ArrayList<>();
}
