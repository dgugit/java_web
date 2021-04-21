package com.comunal.service;

import com.comunal.model.User;
import com.comunal.rest.dto.CreateRequestDTO;
import com.comunal.rest.dto.RequestDTO;

import java.util.List;

public interface RequestService {
    void create(CreateRequestDTO requestDTO, User user);
    List<RequestDTO> getAllByUser(User user);
    List<RequestDTO> getAll();
    void updateRequest(Long requestId, Long teamId);
}
