package ru.spb.vika.models;

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
public class Condition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotBlank(message = "Condition name must not be empty")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_achieved")
    private boolean isAchieved;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task relatedTask;
}
