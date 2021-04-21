package com.comunal.service;

import com.comunal.rest.dto.*;

import java.util.List;

public interface UserService {
    UserDTO registerUser(CreateUserDTO userDTO);
    UserDTO sigIn(String login, String password);
    UserDTO updateUser(UserDTO userDTO, String currLogin);
    UserDTO updatePassword(UpdatePasswordDTO userDTO, String currLogin);
    void createRequest(CreateRequestDTO requestDTO, UserDTO userDTO);
    List<RequestDTO> getAllForUser(UserDTO userDTO);
}
