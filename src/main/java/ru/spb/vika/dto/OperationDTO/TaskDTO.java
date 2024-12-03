package ru.spb.vika.dto.OperationDTO;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TaskDTO {

    private Integer id;

    @NotBlank(message = "Task name must not be empty")
    private String taskName;

    @NotBlank(message = "Serial number must not be empty")
    private String serialNumber;

    @NotBlank(message = "Location must not be empty")
    private String location;

    @Nullable
    private String enemyInfo;

    @NotBlank(message = "Description must not be empty")
    private String description;

    @NotBlank(message = "Pass code must not be empty")
    private String passCode;

    private String previousTasksIDs;

    private List<ActionDTO> actions = new ArrayList<>();

    private List<ConditionDTO> conditions = new ArrayList<>();
}
