package ru.spb.vika.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.spb.vika.dto.OperationDTO.OperationDTO;
import ru.spb.vika.dto.ServerMediaDTO;
import ru.spb.vika.models.Operation;
import ru.spb.vika.models.ServerMedia;
import ru.spb.vika.repositories.MediaRepository;
import ru.spb.vika.repositories.OperationsRepository;
import ru.spb.vika.util.Tools;

@Service
public class UploadService {
    private final OperationsRepository operationsRepository;
    private final MediaRepository mediaRepository;
    private final Tools tools;

    @Autowired
    public UploadService(OperationsRepository operationsRepository, MediaRepository mediaRepository, Tools tools) {
        this.operationsRepository = operationsRepository;
        this.mediaRepository = mediaRepository;
        this.tools = tools;
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

    @Transactional
    public String saveMedia(ServerMediaDTO serverMediaDTO, Integer operationId, String mediaType) {
        mediaRepository.save(ServerMedia.builder()
                .id(serverMediaDTO.getMediafile().getId())
                .taskId(serverMediaDTO.getTaskID())
                .operationId(operationId)
                .name(serverMediaDTO.getMediafile().getName())
                .content(serverMediaDTO.getMediafile().getStringifiedUint8ListFile())
                .mediaType(tools.getMediaType(mediaType))
                .build()
        );
        return "Media successfully saved";
    }
}
