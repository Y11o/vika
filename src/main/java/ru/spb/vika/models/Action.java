package ru.spb.vika.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

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

    @Column(name = "action")
    @NotBlank(message = "Action name must not be empty")
    private String action;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task relatedTask;
}
