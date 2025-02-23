package ru.spb.vika.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spb.vika.models.Operation;
import ru.spb.vika.repositories.MediaRepository;
import ru.spb.vika.repositories.OperationsRepository;
import ru.spb.vika.util.ItemNotFoundException;

@Service
public class OperationService {

    private final OperationsRepository operationsRepository;
    private final MediaRepository mediaRepository;

    @Autowired
    public OperationService(OperationsRepository operationsRepository, MediaRepository mediaRepository) {
        this.operationsRepository = operationsRepository;
        this.mediaRepository = mediaRepository;
    }

    @Transactional
    public void deleteAll() {
        operationsRepository.deleteAll();
        mediaRepository.deleteAll();
    }

    @Transactional
    public Operation deleteById(Integer id) {
        Operation operationToDelete = operationsRepository.findById(id).orElseThrow(
                () -> new ItemNotFoundException("Operation with id " + id + " was not found!")
        );
        operationsRepository.deleteById(id);
        mediaRepository.deleteAllByOperationId(id);
        return operationToDelete;
    }
}
