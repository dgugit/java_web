package com.comunal.rest;

import com.comunal.model.User;
import com.comunal.model.enums.Authority;
import com.comunal.repository.UserRepository;
import com.comunal.rest.dto.CreateUserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@AutoConfigureMockMvc
class UserControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void cleanUpUsers() {
        userRepository.deleteAll();
    }

    @Test
    void addNewUser() throws Exception {
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setLogin("login1");
        createUserDTO.setFirstName("Firstname");
        createUserDTO.setLastName("Lastname");
        createUserDTO.setPassword("password");
        createUserDTO.setConfPassword("password");
        createUserDTO.setAuthority(Authority.CLIENT);

        final String response = mockMvc.perform(post("/user")
                .contentType("application/json")
                .content(OBJECT_MAPPER.writeValueAsString(createUserDTO)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        User createdUser = userRepository.findAll().iterator().next();
        assertEquals("Вітаємо! Вас успішно зареєстровано.", response);
        assertEquals(createUserDTO.getLogin(), createdUser.getLogin());
        assertEquals(createUserDTO.getFirstName(), createdUser.getFirstName());
        assertEquals(createUserDTO.getLastName(), createdUser.getLastName());
        assertEquals(createUserDTO.getAuthority(), createdUser.getAuthority());

    }

    @Test
    void addNewUser_InvalidRequestBody() throws Exception {
        mockMvc.perform(post("/user")
                .contentType("application/json")
                .content("{invalid: \"dto\"}"))
                .andExpect(status().isBadRequest());

        assertFalse(userRepository.findAll().iterator().hasNext());
    }
}