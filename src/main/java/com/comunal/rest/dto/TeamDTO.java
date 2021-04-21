package com.comunal.rest.dto;

import com.comunal.model.Team;

import java.io.Serializable;

public class TeamDTO implements Serializable {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public TeamDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TeamDTO setName(String name) {
        this.name = name;
        return this;
    }

    public static TeamDTO makeDTO(Team team) {
        return new TeamDTO()
                .setId(team.getId())
                .setName(team.getName());
    }
}
