package ru.spb.vika.models;

import jakarta.persistence.*;
import lombok.*;
import ru.spb.vika.util.MediaType;

@Entity
@Table(name = "media")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ServerMedia {

    @Id
    private Integer id;

    @Column(name = "task_id")
    private Integer taskId;

    @Column(name = "operation_id")
    private Integer operationId;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "media_type")
    @Enumerated(EnumType.ORDINAL)
    private MediaType mediaType;
}
