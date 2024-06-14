package org.example.ecommerce.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import org.example.ecommerce.database.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.algorithm.key}")
    private String key;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry}")
    private long expiry;
    private Algorithm algorithm;
    private static final String USERNAME = "USERNAME";

    @PostConstruct
    public void init() {
        algorithm = Algorithm.HMAC256(key);
    }

    public String generateJWT(User user) {
        return JWT.create()
                .withClaim(USERNAME, user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * expiry)))
                .withIssuer(issuer)
                .sign(algorithm);
    }
}
