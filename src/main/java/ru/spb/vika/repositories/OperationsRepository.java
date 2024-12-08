package ru.spb.vika.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.spb.vika.models.Operation;

import java.util.List;
import java.util.Optional;

@Repository
public interface OperationsRepository extends CrudRepository<Operation, Integer> {
    List<Operation> findAll();
}
