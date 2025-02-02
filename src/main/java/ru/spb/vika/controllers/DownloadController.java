package ru.spb.vika.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.spb.vika.services.DownloadService;
import ru.spb.vika.util.ErrorResponse;
import ru.spb.vika.util.ItemNotFoundException;

@RestController
@RequestMapping("/download")
public class DownloadController {

    private final DownloadService downloadService;

    @Autowired
    public DownloadController(DownloadService downloadService) {
        this.downloadService = downloadService;
    }

    @GetMapping("/min-data")
    public ResponseEntity<?> downloadOperationsMinData() {
        return downloadService.getMinData();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> downloadOperationById(@PathVariable Integer id) {
        return downloadService.getById(id);
    }

    @GetMapping("/{operationId}/{taskId}/{mediaType}")
    public ResponseEntity<?> downloadMedia(@PathVariable("operationId") Integer operationId,
                                           @PathVariable("taskId") Integer taskId,
                                           @PathVariable("mediaType") String mediaType) {
        return ResponseEntity.ok(downloadService.getMedia(operationId, taskId, mediaType));
    }

    @ExceptionHandler(value = ItemNotFoundException.class)
    protected ResponseEntity<?> handleItemNotFoundException(ItemNotFoundException e) {
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
