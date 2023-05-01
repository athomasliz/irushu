package org.irushu.login;

import org.irushu.login.service.JWTService;
import org.irushu.login.web.controller.LoginController;
import org.irushu.login.web.model.UserCredentials;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ControllerTests {

    @Spy
    JWTService jwtService;

    @Mock
    AuthenticationManager authenticationManager;

    @InjectMocks
    private LoginController loginController;

    @Test
    void testControllers(){
        when(authenticationManager.authenticate(any())).thenReturn(new Authentication(){
            @Override
            public String getName() {
                return "thomasli";
            }

            @Override
            public boolean implies(Subject subject) {
                return Authentication.super.implies(subject);
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return new ArrayList<>();
            }

            @Override
            public Object getCredentials() {
                return "password";
            }

            @Override
            public Object getDetails() {
                return "details";
            }

            @Override
            public Object getPrincipal() {
                return "thomasli";
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }
        });

        //when(jwtService.getToken(any())).thenReturn(new JWTService().getToken(any()));

        ResponseEntity entity = loginController.getToken(new UserCredentials("thomasli", "password"));

        // Verify if jwtService is called once and only once.
        verify(jwtService,times(1)).getToken(anyString());

        // Verify if the beareer token is not null;
        assertNotEquals( List.of("Bearer null"), entity.getHeaders().get("Authorization"));

        // Capture and verify if the argument of getToken is equals to username
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(jwtService).getToken(argumentCaptor.capture());
        assertEquals("thomasli", argumentCaptor.getValue());
    }

}
