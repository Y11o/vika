package ru.spb.vika.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonIgnoreProperties({"teamClass", "task_id"})
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer task_id;

    private int id;

    @Column(name = "task_name")
    @NotBlank(message = "Task name must not be empty")
    private String taskName;

    @Column(name = "serial_number")
    @NotBlank(message = "Serial number must not be empty")
    private String serialNumber;

    @Column(name = "location")
    private String location;

    @Column(name = "enemy_info")
    @Nullable
    private String enemyInfo;

    @Column(name = "description")
    private String description;

    @Column(name = "alt_description")
    private String altDescription;

    @Column(name = "pass_code")
    private String passCode;

    @Column(name = "previous_tasks_ids")
    private String prevTasksIDs;

    @Column(name = "is_ending")
    @JsonProperty("isEnding")
    private boolean isEnding;

    @Column(name = "all_conditions_required")
    private boolean allCondsRequired;

    @OneToMany(mappedBy = "relatedTask", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Action> actions = new ArrayList<>();

    @OneToMany(mappedBy = "relatedTask", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Condition> conditions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "team_id")
    private Team teamClass;
}
