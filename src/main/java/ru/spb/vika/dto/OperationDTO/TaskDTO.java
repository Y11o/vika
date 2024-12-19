package ru.spb.vika.dto.OperationDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    private String location;

    @Nullable
    private String enemyInfo;

    private String description;

    private String altDescription;

    private String passCode;

    private String prevTasksIDs;

    @JsonProperty("isEnding")
    private boolean isEnding;

    private boolean allCondsRequired;

    private List<ActionDTO> actions = new ArrayList<>();

    private List<ConditionDTO> conditions = new ArrayList<>();
}
