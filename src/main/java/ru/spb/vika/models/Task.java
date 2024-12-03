package ru.spb.vika.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "task_name")
    @NotBlank(message = "Task name must not be empty")
    private String taskName;

    @Column(name = "serial_number")
    @NotBlank(message = "Serial number must not be empty")
    private String serialNumber;

    @Column(name = "location")
    @NotBlank(message = "Location must not be empty")
    private String location;

    @Column(name = "enemy_info")
    @Nullable
    private String enemyInfo;

    @Column(name = "desc")
    @NotBlank(message = "Description must not be empty")
    @Lob
    private String desc;

    @Column(name = "pass_code")
    @NotBlank(message = "Pass code must not be empty")
    private String passCode;

    @Column(name = "previous_tasks_ids")
    private String previousTasksIDs;

    @OneToMany(mappedBy = "relatedTask")
    private List<Action> actions = new ArrayList<>();

    @OneToMany(mappedBy = "relatedTask")
    private List<Condition> conditions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team teamClass;
}