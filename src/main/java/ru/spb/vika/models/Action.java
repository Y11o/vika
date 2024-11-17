package ru.spb.vika.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import ru.spb.vika.util.ActionType;

@Entity
@Table(name = "actions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotBlank(message = "Action name must not be empty")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_complete")
    private boolean isComplete;

    @Column(name = "action_results")
    private String actionNumericResults;

    @Enumerated(EnumType.STRING)
    @Column(name = "action_type")
    private ActionType actionType;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task relatedTask;
}
