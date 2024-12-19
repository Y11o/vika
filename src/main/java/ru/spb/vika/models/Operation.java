package ru.spb.vika.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"operation_id"})
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer operation_id;

    private int id;

    @Column(name = "ops_name")
    @NotBlank(message = "Operation name must not be empty")
    private String opsName;

    @OneToMany(mappedBy = "relatedOperation")
    private List<Team> teamClasses = new ArrayList<>();
}
