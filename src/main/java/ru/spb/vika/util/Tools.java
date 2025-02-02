package ru.spb.vika.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Component
public class Tools {
    public MediaType getMediaType(String mediaType) {
        return switch (mediaType) {
            case "images" -> MediaType.IMAGE;
            case "audios" -> MediaType.AUDIO;
            case "videos" -> MediaType.VIDEO;
            default -> MediaType.FILE;
        };
    }

    public String buildErrorMessage(BindingResult bindingResult) {
        StringBuilder errorMessage = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error:  errors) {
            errorMessage.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append("; ");
        }
        return errorMessage.toString();
    }
}
