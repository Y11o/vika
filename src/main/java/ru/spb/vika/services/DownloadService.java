package ru.spb.vika.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.spb.vika.dto.OperationDTO.OperationMinDataResponse;
import ru.spb.vika.dto.ServerMediaResponse;
import ru.spb.vika.models.Operation;
import ru.spb.vika.models.ServerMedia;
import ru.spb.vika.repositories.MediaRepository;
import ru.spb.vika.repositories.OperationsRepository;
import ru.spb.vika.util.ItemNotFoundException;
import ru.spb.vika.util.Tools;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DownloadService {

    private final OperationsRepository operationsRepository;
    private final MediaRepository mediaRepository;
    private final Tools tools;

    @Autowired
    public DownloadService(OperationsRepository operationsRepository, MediaRepository mediaRepository, Tools tools) {
        this.operationsRepository = operationsRepository;
        this.mediaRepository = mediaRepository;
        this.tools = tools;
    }

    @Transactional
    public ResponseEntity<?> getById(Integer id) {
        Operation operation = operationsRepository.findById(id).orElseThrow(
                () -> new ItemNotFoundException("Operation with id " + id + " was not found!")
        );
        return ResponseEntity.ok(operation);
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

    @Transactional
    public List<ServerMediaResponse> getMedia(Integer operationId, Integer taskId, String mediaType) {
        List<ServerMedia> serverMedias = mediaRepository.findByTaskIdAndOperationIdAndMediaType(taskId, operationId, tools.getMediaType(mediaType));
        if (serverMedias.isEmpty()) {
            throw new ItemNotFoundException("Media file with operation id " + operationId +
                    "\ntask id " + taskId + "\nand mediaType " + mediaType + " was not found!");
        }
        List<ServerMediaResponse> response = new LinkedList<>();
        serverMedias.forEach(serverMedia ->
        response.add(
                ServerMediaResponse.builder()
                        .uploadedFileId(serverMedia.getUploadedFileId())
                        .name(serverMedia.getName())
                        .content(serverMedia.getContent())
                        .taskID(serverMedia.getTaskId())
                        .build()
        ));
        return response;
    }
}