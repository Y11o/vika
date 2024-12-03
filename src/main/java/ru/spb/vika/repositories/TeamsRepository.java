package ru.spb.vika.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.spb.vika.models.Team;

@Repository
public interface TeamsRepository extends CrudRepository<Team, Integer> {
}
