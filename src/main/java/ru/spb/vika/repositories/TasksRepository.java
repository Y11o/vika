package ru.spb.vika.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.spb.vika.models.Task;

@Repository
public interface TasksRepository extends CrudRepository<Task, Integer> {
}
