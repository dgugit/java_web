package com.comunal.service.impl;

import com.comunal.model.Team;
import com.comunal.model.enums.WorkType;
import com.comunal.repository.TeamRepository;
import com.comunal.rest.dto.CreateTeamDTO;
import com.comunal.rest.dto.TeamDTO;
import com.comunal.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public void create(CreateTeamDTO createTeamDTO) {
        Team team = new Team()
                .setName(createTeamDTO.getName())
                .setType(WorkType.getById(createTeamDTO.getTypeId()));
        teamRepository.save(team);
    }

    @Override
    public List<TeamDTO> getAllByTypeId(Long id) {
        return teamRepository.findAllByType(WorkType.getById(id))
                .stream()
                .map(TeamDTO::makeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Team find(Long id) {
        return teamRepository.findOne(id);
    }
}
