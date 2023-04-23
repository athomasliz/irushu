package org.irushu.demo.web.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class
UserCredentials {

    @Schema(example= "thomasli", title="User name")
    private String username;

    @Schema(example= "password", title="password")
    private String password;

}
