package ru.spb.vika.dto.OperationDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class OperationDTO {

    private Integer id;

    @NotBlank(message = "Operation name must not be empty")
    private String name;

    @NotBlank(message = "Operation file must not be null")
    private MultipartFile file;
}
