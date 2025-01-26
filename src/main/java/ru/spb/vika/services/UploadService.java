package ru.spb.vika.services;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.spb.vika.dto.OperationDTO.*;
import ru.spb.vika.models.*;
import ru.spb.vika.repositories.*;
import ru.spb.vika.util.OperationNotCreatedException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadService {
    private final OperationsRepository operationsRepository;

    @Autowired
    public UploadService(OperationsRepository operationsRepository) {
        this.operationsRepository = operationsRepository;
    }

    @Transactional
    public ResponseEntity<?> saveOperation(OperationDTO operationRequest) {
        operationsRepository.save(Operation.builder()
                .id(operationRequest.getId())
                .name(operationRequest.getName())
                .operation(operationRequest.getOperation())
                .build());
        return ResponseEntity.ok("Operation successfully saved!");
    }
}
