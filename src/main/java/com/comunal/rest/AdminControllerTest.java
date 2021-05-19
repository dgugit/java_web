package com.comunal.rest;

import com.comunal.model.Team;
import com.comunal.model.enums.WorkType;
import com.comunal.repository.TeamRepository;
import com.comunal.rest.dto.CreateTeamDTO;
import com.comunal.rest.dto.TeamDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@AutoConfigureMockMvc
class AdminControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TeamRepository teamRepository;

    @AfterEach
    void cleanUpTeams() {
        teamRepository.deleteAll();
    }

    @Test
    void addNewTeam() throws Exception {
        CreateTeamDTO createTeamDto = getCreateTeamDto(WorkType.ELECTRIC);

        final String response = mockMvc.perform(post("/admin/team")
                .contentType("application/json")
                .content(OBJECT_MAPPER.writeValueAsString(createTeamDto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Team createdTeam = teamRepository.findAll().iterator().next();
        assertEquals("Бригада успішно створена.", response);
        assertEquals(createTeamDto.getName(), createdTeam.getName());
        assertEquals(createTeamDto.getTypeId(), createdTeam.getType().getId());
    }

    @Test
    void addNewTeam_InvalidRequestBody() throws Exception {
        mockMvc.perform(post("/admin/team")
                .contentType("application/json")
                .content("{invalid: \"dto\"}"))
                .andExpect(status().isBadRequest());

        assertFalse(teamRepository.findAll().iterator().hasNext());
    }

    @Test
    void getTeams() throws Exception {
        Team team1 = new Team().setName("team1").setType(WorkType.ELECTRIC);
        Team team2 = new Team().setName("team2").setType(WorkType.ELECTRIC);
        Team team3 = new Team().setName("team3").setType(WorkType.HEATING);
        team1 = teamRepository.save(team1);
        team2 = teamRepository.save(team2);
        team3 = teamRepository.save(team3);

        final String responseElectric = mockMvc.perform(get("/admin/teams/{id}", WorkType.ELECTRIC.getId()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        List<TeamDTO> teamsElectric = OBJECT_MAPPER.readValue(responseElectric, new TypeReference<List<TeamDTO>>() {
        });
        assertEquals(2, teamsElectric.size());
        assertTrue(teamsElectric.contains(TeamDTO.makeDTO(team1)));
        assertTrue(teamsElectric.contains(TeamDTO.makeDTO(team2)));

        final String responseHeating = mockMvc.perform(get("/admin/teams/{id}", WorkType.HEATING.getId()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        List<TeamDTO> teamsHeating = OBJECT_MAPPER.readValue(responseHeating, new TypeReference<List<TeamDTO>>() {
        });
        assertEquals(1, teamsHeating.size());
        assertTrue(teamsHeating.contains(TeamDTO.makeDTO(team3)));
    }

    private static CreateTeamDTO getCreateTeamDto(WorkType workType) {
        CreateTeamDTO createTeamDTO = new CreateTeamDTO();
        createTeamDTO.setName("newTeam");
        createTeamDTO.setTypeId(workType.getId());
        return createTeamDTO;
    }
}