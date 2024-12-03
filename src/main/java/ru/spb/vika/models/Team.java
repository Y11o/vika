package ru.spb.vika.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties({"relatedOperation"})
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "class_name")
    @NotBlank(message = "Class name must not be empty")
    private String className;

    @Column(name = "class_access_code")
    @NotBlank(message = "Class access code must not be empty")
    private String classAccessCode;

    @OneToMany(mappedBy = "teamClass")
    private List<Task> relatedTasks = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "operation_id", referencedColumnName = "id")
    private Operation relatedOperation;
}
