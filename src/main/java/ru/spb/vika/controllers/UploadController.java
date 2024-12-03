package ru.spb.vika.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.spb.vika.dto.OperationDTO.OperationDTO;
import ru.spb.vika.services.UploadService;
import ru.spb.vika.util.ErrorResponse;
import ru.spb.vika.util.OperationNotCreatedException;

import java.util.List;

@RestController
@RequestMapping("/upload")
public class UploadController {

    private final UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/operation")
    public ResponseEntity<?> uploadData(@Valid @RequestBody OperationDTO operationRequest, BindingResult bindingResult) {
        if (bindingResult != null && bindingResult.hasErrors()) {
            throw new OperationNotCreatedException(buildErrorMessage(bindingResult));
        }
        return uploadService.saveOperation(operationRequest);
    }

    @ExceptionHandler(value = OperationNotCreatedException.class)
    protected ResponseEntity<?> handleUserNotCreatedException(OperationNotCreatedException e) {
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private String buildErrorMessage(BindingResult bindingResult) {
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
