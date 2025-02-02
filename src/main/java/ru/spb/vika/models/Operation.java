package ru.spb.vika.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "operations")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Operation {

    @Id
    private int id;

    @Column(name = "name")
    @NotBlank(message = "Operation name must not be empty")
    private String name;

    @Lob
    @Column(name = "operation")
    private String operation;
}
