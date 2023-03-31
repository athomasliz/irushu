package org.irushu.login.web.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserCredentials {
    @Schema(example= "thomasli", required = false, title="User name")
    private String username;
    @Schema(example= "password", required = false, title="password")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
