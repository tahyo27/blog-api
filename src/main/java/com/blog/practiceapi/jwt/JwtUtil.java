package com.blog.practiceapi.jwt;

import com.blog.practiceapi.config.StrDataConfig;
import com.blog.practiceapi.exception.JwtNotFoundException;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

public class JwtUtil {
    private final SecretKey secretKey;
    private StrDataConfig strDataConfig;

    public JwtUtil() {
        String jwtStrKey = Optional.ofNullable(strDataConfig.getJwtStrKey())
                .orElseThrow(JwtNotFoundException::new);
        this.secretKey = new SecretKeySpec(jwtStrKey
                .getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getUsername(String token) {
        return  Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token)
                .getPayload().get("username", String.class);
    }

    public String getRole(String token) {

        return Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token)
                .getPayload().get("role", String.class);
    }

    public Boolean isExpired(String token) { //만료 시간 체크

        return Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token)
                .getPayload().getExpiration()
                .before(Date.from(Instant.from(LocalDateTime.now())));
    }

    public String createJwt(String username, String role, Long expired) {
        return Jwts.builder()
                .claim("username", username)
                .claim("role", role)
                .issuedAt(Date.from(Instant.from(LocalDateTime.now())))
                .expiration(Date.from(Instant.from(LocalDateTime.now().plusSeconds(expired))))
                .signWith(secretKey)
                .compact();
    }

}
