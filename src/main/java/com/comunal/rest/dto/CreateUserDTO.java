package com.comunal.rest.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CreateUserDTO extends UserDTO implements Serializable {

    @NotNull(message = "Password cannot be null")
    private String password;

    @NotNull(message = "Password cannot be null")
    private String confPassword;

    public String getPassword() {
        return password;
    }

    public CreateUserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfPassword() {
        return confPassword;
    }

    public CreateUserDTO setConfPassword(String confPassword) {
        this.confPassword = confPassword;
        return this;
    }
}
