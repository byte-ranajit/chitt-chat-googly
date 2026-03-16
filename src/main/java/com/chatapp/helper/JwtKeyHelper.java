package com.chatapp.helper;

import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
@Data
public class JwtKeyHelper {

    @Value("${jwt.secret}")
    private final String SECRET_KEY;

    @Value("${jwt.expiration}")
    private final long JWT_EXPIRATION ;

    public JwtKeyHelper(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.expiration}") long jwtExpiration) {

        this.SECRET_KEY = secretKey;
        this.JWT_EXPIRATION = jwtExpiration;
    }

    public SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

}
