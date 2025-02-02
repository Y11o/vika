package ru.spb.vika.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.spb.vika.models.ServerMedia;
import ru.spb.vika.util.MediaType;

import java.util.List;

public interface MediaRepository extends CrudRepository<ServerMedia, Integer> {
    List<ServerMedia> findByTaskIdAndOperationIdAndMediaType(Integer taskId, Integer operationId, MediaType mediaType);
}
