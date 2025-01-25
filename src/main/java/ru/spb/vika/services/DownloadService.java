package ru.spb.vika.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.spb.vika.dto.OperationMinDataResponse;
import ru.spb.vika.models.Operation;
import ru.spb.vika.repositories.OperationsRepository;
import ru.spb.vika.util.ItemNotFoundException;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Service
public class DownloadService {

    private final OperationsRepository operationsRepository;

    @Autowired
    public DownloadService(OperationsRepository operationsRepository) {
        this.operationsRepository = operationsRepository;
    }

    @Transactional
    public ResponseEntity<?> getById(Integer id) {
        Operation operation = operationsRepository.findById(id).orElseThrow(
                () -> new ItemNotFoundException("Operation with id " + id + " was not found!")
        );
        if (operation.getFileName() == null) {
            return new ResponseEntity<>("Operation file has not been uploaded", HttpStatus.NOT_FOUND);
        }
        Path filePath = Path.of(operation.getFileName());
        if (!Files.exists(filePath)) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath.toFile());
            InputStreamResource resource = new InputStreamResource(fileInputStream);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filePath.getFileName() + "\"")
                    .contentLength(Files.size(filePath))
                    .body(resource);
        } catch (IOException e) {
            return new ResponseEntity<>("File read error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getMinData() {
        return ResponseEntity.ok(operationsRepository.findAll().stream().map(
                this::operationMinDataResponseBuilder
        ).collect(Collectors.toList()));
    }

    private OperationMinDataResponse operationMinDataResponseBuilder(Operation operation) {
        return OperationMinDataResponse.builder()
                .id(operation.getId())
                .opsName(operation.getName())
                .build();
    }
}
