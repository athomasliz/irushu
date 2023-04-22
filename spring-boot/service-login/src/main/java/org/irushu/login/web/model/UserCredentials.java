package org.irushu.login.web.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserCredentials {

    @Schema(example= "thomasli", title="User name")
    private String username;

    @Schema(example= "password", title="password")
    private String password;

}
