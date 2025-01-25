package ru.spb.vika.services;

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
    @Value("${upload_dir_operations}")
    private String UPLOAD_DIR;

    @Autowired
    public UploadService(OperationsRepository operationsRepository) {
        this.operationsRepository = operationsRepository;
    }

    public ResponseEntity<?> saveOperation(OperationDTO operationRequest, MultipartFile file) {
        if(file == null || file.isEmpty()){
            throw new IllegalArgumentException("Файл не может быть пустым.");
        }

        String contentType = file.getContentType();
        String allowedTypes = "multipart/form-data";
        if(!allowedTypes.equals(contentType)){
            throw new IllegalArgumentException("Тип файла " + contentType + " не поддерживается. Возможные типы: " + allowedTypes);
        }

        Path opsDirPath = Paths.get(UPLOAD_DIR, operationRequest.getName() + "_" + operationRequest.getId());
        File opsDir = opsDirPath.toFile();
        if (!opsDir.exists()) {
            opsDir.mkdirs();
        }

        try (InputStream inputStream = file.getInputStream();
             OutputStream outputStream = Files.newOutputStream(opsDirPath)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            operationsRepository.save(Operation.builder()
                    .id(operationRequest.getId())
                    .name(operationRequest.getName())
                    .fileName(opsDirPath.toString())
                    .build());
            return ResponseEntity.ok("Operation successfully saved!");
        } catch (NullPointerException | NumberFormatException | ConstraintViolationException | IOException exception) {
            throw new OperationNotCreatedException(exception.getMessage());
        }
    }
}
