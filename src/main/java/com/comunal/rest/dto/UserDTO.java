package com.comunal.rest.dto;

import com.comunal.model.User;
import com.comunal.model.enums.Authority;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class UserDTO implements Serializable {
    private static final String NAME_REGEX = "^[A-Z][a-z]{2,14}$";

    @Pattern(regexp = NAME_REGEX, message = "First name must match \"^[A-Z][a-z]{2,14}$\" ")
    @NotNull(message = "First name cannot be null")
    private String firstName;

    @Pattern(regexp = NAME_REGEX, message = "Last name must match \"^[A-Z][a-z]{2,14}$\" ")
    @NotNull(message = "Last name cannot be null")
    private String lastName;

    @NotNull(message = "Login cannot be null")
    private String login;

    private Authority authority;

    public String getFirstName() {
        return firstName;
    }

    public UserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public UserDTO setLogin(String login) {
        this.login = login;
        return this;
    }

    public Authority getAuthority() {
        return authority;
    }

    public UserDTO setAuthority(Authority authority) {
        this.authority = authority;
        return this;
    }

    public static UserDTO makeDTO(User user) {
        return new UserDTO()
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setAuthority(user.getAuthority())
                .setLogin(user.getLogin());
    }
}
