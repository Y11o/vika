package ru.spb.vika.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "conditions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties({"relatedTask", "condition_id"})
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer condition_id;

    private int id;

    @Column(name = "condition")
    @NotBlank(message = "Condition name must not be empty")
    private String condition;

    @Column(name = "description")
    private String description;

    @Column(name = "passed")
    private boolean passed;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task relatedTask;
}
