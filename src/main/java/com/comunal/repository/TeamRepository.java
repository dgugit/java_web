package com.comunal.repository;

import com.comunal.model.Team;
import com.comunal.model.enums.WorkType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Long> {
    List<Team> findAllByType(WorkType type);
}
