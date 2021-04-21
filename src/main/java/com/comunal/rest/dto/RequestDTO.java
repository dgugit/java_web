package com.comunal.rest.dto;

import com.comunal.model.Request;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class RequestDTO extends CreateRequestDTO implements Serializable {

    private Long id;

    private String type;

    private String status;

    private String user;

    private String team;

    @NotNull
    private Long teamId;

    public Long getId() {
        return id;
    }

    public RequestDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public RequestDTO setType(String type) {
        this.type = type;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public RequestDTO setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getUser() {
        return user;
    }

    public RequestDTO setUser(String user) {
        this.user = user;
        return this;
    }

    public String getTeam() {
        return team;
    }

    public RequestDTO setTeam(String team) {
        this.team = team;
        return this;
    }

    public Long getTeamId() {
        return teamId;
    }

    public RequestDTO setTeamId(Long teamId) {
        this.teamId = teamId;
        return this;
    }

    public static RequestDTO makeDTO(Request request) {
        RequestDTO dto = new RequestDTO();
        dto.setId(request.getId())
                .setStatus(request.getStatus().getName())
                .setType(request.getType().getName())
                .setUser(request.getUser().getFullName());

        if (request.getTeam() != null) {
            dto.setTeam(request.getTeam().getName());
        }
        dto.setAmount(request.getAmount())
                .setDescription(request.getDescription())
                .setDate(request.getDate())
                .setTypeId(request.getType().getId());
        return dto;
    }
}
