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

    @Column(name = "location")
    @NotBlank(message = "Location must not be empty")
    private String location;

    @Column(name = "enemy")
    @Nullable
    private String enemy;

    @Column(name = "description")
    @NotBlank(message = "Description must not be empty")
    @Lob
    private String description;

    @Column(name = "activation_code")
    @NotBlank(message = "Activation code must not be empty")
    private String activationCode;

    @OneToMany(mappedBy = "relatedTask")
    private List<Action> actions = new ArrayList<>();

    @OneToMany(mappedBy = "relatedTask")
    private List<Condition> conditions = new ArrayList<>();

    @Nullable
    @OneToOne
    @JoinColumn(name = "previous_task_id", referencedColumnName = "id")
    private Task previousTask;

    @ManyToOne
    @JoinColumn(name = "operation_id", referencedColumnName = "id")
    private Operation relatedOperation;
}
