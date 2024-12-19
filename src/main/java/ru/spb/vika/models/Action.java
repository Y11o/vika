package ru.spb.vika.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"relatedTask", "action_id"})
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer action_id;

    private int id;

    @Column(name = "action")
    @NotBlank(message = "Action name must not be empty")
    private String action;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
    private Task relatedTask;
}
