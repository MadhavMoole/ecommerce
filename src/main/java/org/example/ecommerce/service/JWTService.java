package org.example.ecommerce.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.annotation.Nonnull;
import jakarta.annotation.PostConstruct;
import org.example.ecommerce.database.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    private String key = "SuperSecureSecretKey";
    private String issuer = "eCommerce";
    private long expiry = 86400;
    private Algorithm algorithm;
    private static final String USERNAME = "USERNAME";
    private static final String EMAIL = "EMAIL";
    private static final String RESET_PASSWORD = "RESET_PASSWORD";

    @PostConstruct
    public void init() {
        algorithm = Algorithm.HMAC256(key);
    }

    public String generateJWT(@Nonnull User user) {
        return JWT.create()
                .withClaim(USERNAME, user.getUsername())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * expiry)))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String generateVerificationJWT(@Nonnull User user) {
        return JWT.create()
                .withClaim(EMAIL, user.getEmail())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * expiry)))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String generatePasswordJWT(@Nonnull User user) {
        return JWT.create()
                .withClaim(RESET_PASSWORD, user.getEmail())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * 60 * 15)))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String getResetPasswordEmail(String token) {
        DecodedJWT jwt = JWT.require(algorithm).build().verify(token); 
        return jwt.getClaim(RESET_PASSWORD).asString();
    }

    public String getUsername(String token) {
        DecodedJWT jwt = JWT.require(algorithm).build().verify(token); 
        return jwt.getClaim(USERNAME).asString();
    }
}
