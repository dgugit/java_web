package com.comunal.rest.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UpdatePasswordDTO implements Serializable {
    @NotNull(message = "Password cannot be null")
    private String password;

    @NotNull(message = "Password cannot be null")
    private String confPassword;

    @NotNull(message = "Current password cannot be null")
    private String currentPassword;

    public String getPassword() {
        return password;
    }

    public UpdatePasswordDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfPassword() {
        return confPassword;
    }

    public UpdatePasswordDTO setConfPassword(String confPassword) {
        this.confPassword = confPassword;
        return this;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public UpdatePasswordDTO setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
        return this;
    }
}
