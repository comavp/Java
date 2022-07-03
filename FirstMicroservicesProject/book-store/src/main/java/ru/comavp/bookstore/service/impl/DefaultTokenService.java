package ru.comavp.bookstore.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.comavp.bookstore.service.TokenService;

@Service
@Slf4j
public class DefaultTokenService implements TokenService {

    @Value("${auth.jwt.secret}")
    private String secretKey;

    private final String ISSUER = "auth-service";
    private final String AUDIENCE = "bookstore";

    @Override
    public boolean checkToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();

        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            if (!decodedJWT.getIssuer().equals(ISSUER)) {
                log.error("Issue is incorrect");
                return false;
            }

            if (!decodedJWT.getAudience().contains(AUDIENCE)) {
                log.error("Audience is incorrect");
                return false;
            }
        } catch (JWTVerificationException e) {
            log.error("Token is invalid: " + e.getMessage());
            return false;
        }

        return true;
    }
}
