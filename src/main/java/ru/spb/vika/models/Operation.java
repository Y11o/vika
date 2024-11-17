package ru.spb.vika.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "operations")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotBlank(message = "Operation name must not be empty")
    private String name;

    @OneToMany(mappedBy = "relatedOperation")
    private List<Task> operationsTasks = new ArrayList<>();
}
