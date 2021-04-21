package com.comunal.service.impl;

import com.comunal.model.Request;
import com.comunal.model.User;
import com.comunal.model.enums.RequestStatus;
import com.comunal.model.enums.WorkType;
import com.comunal.repository.RequestRepository;
import com.comunal.rest.dto.CreateRequestDTO;
import com.comunal.rest.dto.RequestDTO;
import com.comunal.service.RequestService;
import com.comunal.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private TeamService teamService;

    @Override
    public void create(CreateRequestDTO requestDTO, User user) {
        Request request = new Request()
                .setDate(requestDTO.getDate())
                .setAmount(requestDTO.getAmount())
                .setDescription(requestDTO.getDescription())
                .setType(WorkType.getById(requestDTO.getTypeId()))
                .setUser(user);
        requestRepository.save(request);
    }

    @Override
    public List<RequestDTO> getAllByUser(User user) {
        return requestRepository.findAllByUserOrderByDate(user)
                .stream()
                .map(RequestDTO::makeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RequestDTO> getAll() {
        return requestRepository.findAll()
                .stream()
                .map(RequestDTO::makeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void updateRequest(Long requestId, Long teamId) {
        requestRepository.save(requestRepository.findOne(requestId)
                .setTeam(teamService.find(teamId))
                .setStatus(RequestStatus.DONE));
    }
}
