package ru.spb.vika.services;

import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.spb.vika.dto.OperationDTO.*;
import ru.spb.vika.models.*;
import ru.spb.vika.repositories.*;
import ru.spb.vika.util.OperationNotCreatedException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UploadService {
    private final ActionsRepository actionsRepository;
    private final ConditionsRepository conditionsRepository;
    private final OperationsRepository operationsRepository;
    private final TasksRepository tasksRepository;
    private final TeamsRepository teamsRepository;

    @Autowired
    public UploadService(ActionsRepository actionsRepository, ConditionsRepository conditionsRepository, OperationsRepository operationsRepository, TasksRepository tasksRepository, TeamsRepository teamsRepository) {
        this.actionsRepository = actionsRepository;
        this.conditionsRepository = conditionsRepository;
        this.operationsRepository = operationsRepository;
        this.tasksRepository = tasksRepository;
        this.teamsRepository = teamsRepository;
    }

    public ResponseEntity<?> saveOperation(List<OperationDTO> operationsRequest) {
        try {
            for (OperationDTO operationRequest : operationsRequest) {
                Operation operation = operationsRepository.save(Operation.builder()
                        .id(operationRequest.getId())
                        .opsName(operationRequest.getOpsName())
                        .build());

                List<Team> savedTeams = new ArrayList<>();

                for (TeamDTO teamDTO : operationRequest.getTeamClasses()) {
                    List<Task> savedTasks = new ArrayList<>();
                    Team team = teamBuildAndSave(teamDTO, operation);
                    for (TaskDTO taskDTO : teamDTO.getRelatedTasks()) {
                        List<Action> savedActions = new ArrayList<>();
                        List<Condition> savedConditions = new ArrayList<>();
                        Task task = taskBuildAndSave(taskDTO, team);
                        for (ActionDTO actionDTO : taskDTO.getActions()) {
                            savedActions.add(actionBuildAndSave(actionDTO, task));
                        }
                        for (ConditionDTO conditionDTO : taskDTO.getConditions()) {
                            savedConditions.add(conditionBuildAndSave(conditionDTO, task));
                        }
                        task.setActions(savedActions);
                        task.setConditions(savedConditions);
                        savedTasks.add(task);
                    }
                    team.setRelatedTasks(savedTasks);
                    savedTeams.add(team);
                }
                operation.setTeamClasses(savedTeams);
                operationsRepository.save(operation);
            }
            return ResponseEntity.ok("Operation successfully saved!");
        } catch (NullPointerException | NumberFormatException | ConstraintViolationException exception) {
            throw new OperationNotCreatedException(exception.getMessage());
        }
    }

    private Team teamBuildAndSave(TeamDTO teamDTO, Operation operation) {
        return teamsRepository.save(Team.builder()
                .id(teamDTO.getId())
                .className(teamDTO.getClassName())
                .classAccessCode(teamDTO.getClassAccessCode())
                .relatedOperation(operation)
                .build());
    }

    private Task taskBuildAndSave(TaskDTO taskDTO, Team team) {
        return tasksRepository.save(Task.builder()
                .id(taskDTO.getId())
                .taskName(taskDTO.getTaskName())
                .allCondsRequired(taskDTO.isAllCondsRequired())
                .isEnding(taskDTO.isEnding())
                .altDescription(taskDTO.getAltDescription())
                .serialNumber(taskDTO.getSerialNumber())
                .description(taskDTO.getDescription())
                .location(taskDTO.getLocation())
                .enemyInfo(taskDTO.getEnemyInfo())
                .passCode(taskDTO.getPassCode())
                .prevTasksIDs(taskDTO.getPrevTasksIDs())
                .teamClass(team)
                .build());
    }

    private Action actionBuildAndSave(ActionDTO actionDTO, Task task) {
        return actionsRepository.save(Action.builder()
                .id(actionDTO.getId())
                .action(actionDTO.getAction())
                .description(actionDTO.getDescription())
                .relatedTask(task)
                .build());
    }

    private Condition conditionBuildAndSave(ConditionDTO conditionDTO, Task task) {
        return conditionsRepository.save(Condition.builder()
                .id(conditionDTO.getId())
                .condition(conditionDTO.getCondition())
                .description(conditionDTO.getDescription())
                .passed(conditionDTO.isPassed())
                .relatedTask(task).build());
    }
}
