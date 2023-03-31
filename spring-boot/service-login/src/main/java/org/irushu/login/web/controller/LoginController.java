package org.irushu.login.web.controller;

import org.irushu.login.service.JWTService;
import org.irushu.login.web.model.UserCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    //@CrossOrigin("http://localhost:3000")
    @RequestMapping(value="/login", method= RequestMethod.POST)
    public ResponseEntity<?> getToken(@RequestBody UserCredentials credentials) {

       UsernamePasswordAuthenticationToken creds = new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(),
                        credentials.getPassword());

       Authentication auth = authenticationManager.authenticate(creds);

       // Generate token
       String jwts = jwtService.getToken(auth.getName());

       return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwts)
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,"Authorization")
                .build();
    }

}
