package com.comunal.rest;

import com.comunal.rest.dto.CreateTeamDTO;
import com.comunal.rest.dto.TeamDTO;
import com.comunal.service.RequestService;
import com.comunal.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(path="/admin")
public class AdminController {
    @Autowired
    private TeamService teamService;

    @Autowired
    private RequestService requestService;

    @PostMapping(path="/team")
    public @ResponseBody String addNewTeam (@Valid @RequestBody CreateTeamDTO teamDTO) {
        teamService.create(teamDTO);
        return "Бригада успішно створена.";
    }

    @GetMapping(path="/teams/{id}")
    public @ResponseBody List<TeamDTO> getTeams (@PathVariable Long id) {
        return teamService.getAllByTypeId(id);
    }

    @PostMapping(path="/request")
    public @ResponseBody void updateRequest (@RequestParam Long id, @RequestParam Long teamId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        requestService.updateRequest(id, teamId);
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
    }
}