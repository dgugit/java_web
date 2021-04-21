package com.comunal.service;

import com.comunal.model.Team;
import com.comunal.rest.dto.CreateTeamDTO;
import com.comunal.rest.dto.TeamDTO;

import java.util.List;

public interface TeamService {
    void create(CreateTeamDTO createTeamDTO);
    List<TeamDTO> getAllByTypeId(Long id);
    Team find(Long id);
}
