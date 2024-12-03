package ru.spb.vika.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.spb.vika.dto.OperationMinDataResponse;
import ru.spb.vika.models.Operation;
import ru.spb.vika.repositories.OperationsRepository;
import ru.spb.vika.util.ItemNotFoundException;

import java.util.stream.Collectors;

@Service
public class DownloadService {

    private final OperationsRepository operationsRepository;

    @Autowired
    public DownloadService(OperationsRepository operationsRepository) {
        this.operationsRepository = operationsRepository;
    }

    public ResponseEntity<?> getById(Integer id) {
        return ResponseEntity.ok(operationsRepository.findById(id).orElseThrow(
                () -> new ItemNotFoundException("Operation with id " + id + " was not found!")
        ));
    }

    public ResponseEntity<?> getMinData() {
        return ResponseEntity.ok(operationsRepository.findAll().stream().map(
                this::operationMinDataResponseBuilder
        ).collect(Collectors.toList()));
    }

    private OperationMinDataResponse operationMinDataResponseBuilder(Operation operation) {
        return OperationMinDataResponse.builder()
                .id(operation.getId())
                .opsName(operation.getOpsName())
                .build();
    }
}
