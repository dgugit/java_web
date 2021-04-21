package com.comunal.service.impl;

import com.comunal.encryption.PasswordUtils;
import com.comunal.model.User;
import com.comunal.model.enums.Authority;
import com.comunal.repository.UserRepository;
import com.comunal.rest.dto.*;
import com.comunal.service.RequestService;
import com.comunal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_ALREADY_EXIST_MESSAGE = "User with this login already exist!";
    private static final String PASSWORDS_NOT_EQUAL = "Passwords are not equal!";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestService requestService;

    @Override
    public UserDTO registerUser(CreateUserDTO userDTO) {
        if (!userDTO.getPassword().equals(userDTO.getConfPassword())) {
            throw new RuntimeException(PASSWORDS_NOT_EQUAL);
        }

        if (loginAlreadyExists(userDTO.getLogin())) {
            throw new RuntimeException(USER_ALREADY_EXIST_MESSAGE);
        }

        User user = new User()
                .setFirstName(userDTO.getFirstName())
                .setLastName(userDTO.getLastName())
                .setLogin(userDTO.getLogin())
                .setPassword((PasswordUtils.generateSecurePassword(userDTO.getPassword(), Optional.empty())));
        return UserDTO.makeDTO(userRepository.save(user));
    }

    @Override
    public UserDTO sigIn(String login, String password) {
        User user = userRepository.findByLogin(login).orElseThrow(RuntimeException::new);
        if (!PasswordUtils.verifyUserPassword(password, user.getPassword())) {
            throw new RuntimeException("Login or password is incorrect!");
        }

        return UserDTO.makeDTO(user);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, String currLogin) {
        User user = userRepository.findByLogin(currLogin).orElseThrow(RuntimeException::new);
        user.setFirstName(userDTO.getFirstName())
                .setLastName(userDTO.getLastName())
                .setLogin(userDTO.getLogin());
        return UserDTO.makeDTO(userRepository.save(user));
    }

    @Override
    public UserDTO updatePassword(UpdatePasswordDTO userDTO, String currLogin) {
        User user = userRepository.findByLogin(currLogin).orElseThrow(RuntimeException::new);
        if (!PasswordUtils.verifyUserPassword(userDTO.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("Password is incorrect!");
        }
        if (!userDTO.getPassword().equals(userDTO.getConfPassword())) {
            throw new RuntimeException(PASSWORDS_NOT_EQUAL);
        }
        user.setPassword((PasswordUtils.generateSecurePassword(userDTO.getPassword(), Optional.empty())));
        return UserDTO.makeDTO(userRepository.save(user));
    }

    @Override
    public void createRequest(CreateRequestDTO requestDTO, UserDTO userDTO) {
        User user = userRepository.findByLogin(userDTO.getLogin()).orElseThrow(RuntimeException::new);
        requestService.create(requestDTO, user);
    }

    @Override
    public List<RequestDTO> getAllForUser(UserDTO userDTO) {
        User user = userRepository.findByLogin(userDTO.getLogin()).orElseThrow(RuntimeException::new);

        if (Authority.ADMIN.equals(user.getAuthority())) {
            return requestService.getAll();
        }
        return requestService.getAllByUser(user);
    }

    private boolean loginAlreadyExists(String login) {
        return userRepository.findByLogin(login).isPresent();
    }
}
