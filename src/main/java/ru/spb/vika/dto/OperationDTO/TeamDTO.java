package ru.spb.vika.dto.OperationDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TeamDTO {

    private int id;

    @NotBlank(message = "Class name must not be empty")
    private String className;

    @NotBlank(message = "Class access code must not be empty")
    private String classAccessCode;

    private List<TaskDTO> relatedTasks = new ArrayList<>();
}
