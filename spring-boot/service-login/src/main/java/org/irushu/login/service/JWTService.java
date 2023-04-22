package org.irushu.login.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.KeyStore;
import java.util.Date;

@Slf4j
@Service
public class JWTService {

    static final long EXPIRATIONTIME = 86400000; // 1

    static final String PREFIX = "Bearer";

    // Generate secret key. Only for the demonstration
    // You should read it from the application configuration

    //static Key key = null;
    static Key key = Keys.secretKeyFor
            (SignatureAlgorithm.HS256);

    static {
        try {
            ClassPathResource resource = new ClassPathResource("keystore-demo.jks");
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(resource.getInputStream(), "demo1234".toCharArray());

            key = keystore.getKey("demo-service", "demo1234".toCharArray());

        }
        catch(Throwable t){
            log.error("Keystore initialization Exception", t);
        }
    }

    // Generate signed JWT token
    public String getToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis()  + EXPIRATIONTIME))
                .signWith(key)
                .compact();
    }

    // Get a token from request Authorization header,
    // verify a token and get username
    public String getAuthUser(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null) {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token.replace(PREFIX, ""))
                    .getBody()
                    .getSubject();
        }
        return null;
    }

}