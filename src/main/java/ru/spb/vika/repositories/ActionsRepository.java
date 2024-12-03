package ru.spb.vika.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.spb.vika.models.Action;

@Repository
public interface ActionsRepository extends CrudRepository<Action, Integer> {
}
