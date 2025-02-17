package com.doctortime.doctortime.Security.Service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${secret}")
    private String secret;

    public String tokenGenerator(UserDetails user) {
        try {
            
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("DoctorTime API")
                    .withSubject(user.getUsername())
                    .withExpiresAt(LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00")))
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException(e);
        }
    }
    public String getSubject(String tokenJWT){
        System.out.println(tokenJWT);
        try{
            Algorithm algo = Algorithm.HMAC256(secret);
            return JWT.require(algo)
                    .withIssuer("DoctorTime API")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

}
