package com.movieflix.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.movieflix.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class TokenService {

    @Value("${movieflix.security.secret}")
    private String secret;

    public String generateToken(User user){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("userId", user.getId())
                .withClaim("name", user.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 + 60 + 60 + 24 + 2)) // 2 days
                .withIssuedAt(Instant.now())
                .withIssuer("movieflix-api")
                .sign(algorithm);
    }
}
