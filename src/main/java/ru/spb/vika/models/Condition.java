package ru.spb.vika.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "conditions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "condition")
    @NotBlank(message = "Condition name must not be empty")
    private String condition;

    @Column(name = "description")
    private String description;

    @Column(name = "passed")
    private boolean passed = false;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task relatedTask;
}
