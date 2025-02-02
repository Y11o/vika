package ru.spb.vika.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spb.vika.dto.OperationDTO.OperationDTO;
import ru.spb.vika.dto.ServerMediaDTO;
import ru.spb.vika.services.UploadService;
import ru.spb.vika.util.ErrorResponse;
import ru.spb.vika.util.OperationNotCreatedException;
import ru.spb.vika.util.Tools;

import java.util.List;

@RestController
@RequestMapping("/upload")
public class UploadController {

    private final UploadService uploadService;
    private final Tools tools;

    @Autowired
    public UploadController(UploadService uploadService, Tools tools) {
        this.uploadService = uploadService;
        this.tools = tools;
    }

    @PostMapping("/operation")
    public ResponseEntity<?> uploadData(@Valid @RequestBody OperationDTO operationRequest, BindingResult bindingResult) {
        if (bindingResult != null && bindingResult.hasErrors()) {
            throw new OperationNotCreatedException(tools.buildErrorMessage(bindingResult));
        }
        return uploadService.saveOperation(operationRequest);
    }

    @PostMapping("/operation/{operationId}/{mediaType}")
    public ResponseEntity<?> uploadMedia(@Valid @RequestBody ServerMediaDTO serverMediaDTO,
                                         @PathVariable("operationId") Integer operationId,
                                         @PathVariable("mediaType") String mediaType,
                                         BindingResult bindingResult) {
        if (bindingResult != null && bindingResult.hasErrors()) {
            throw new OperationNotCreatedException(tools.buildErrorMessage(bindingResult));
        }
        return ResponseEntity.ok(uploadService.saveMedia(serverMediaDTO, operationId, mediaType));
    }

    @ExceptionHandler(value = OperationNotCreatedException.class)
    public ResponseEntity<?> handleUserNotCreatedException(OperationNotCreatedException e) {
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
