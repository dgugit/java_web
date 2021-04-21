package com.comunal.rest.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CreateTeamDTO implements Serializable {

    @NotNull
    private String name;

    @Min(1)
    @Max(6)
    private long typeId;


    public String getName() {
        return name;
    }

    public CreateTeamDTO setName(String name) {
        this.name = name;
        return this;
    }

    public long getTypeId() {
        return typeId;
    }

    public CreateTeamDTO setTypeId(long typeId) {
        this.typeId = typeId;
        return this;
    }
}
