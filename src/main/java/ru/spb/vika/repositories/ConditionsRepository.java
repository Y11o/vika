package ru.spb.vika.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.spb.vika.models.Condition;

@Repository
public interface ConditionsRepository extends CrudRepository<Condition, Integer> {
}
