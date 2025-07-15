package com.romecka.fakeforge.application.service.token;

import com.romecka.fakeforge.domain.token.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JWTService implements TokenService {

    @Value("${jwt.secret}")
    private String secKey;

    @Value("${jwt.expiration}")
    private String expiration;

    private Duration expirationDuration;

    public JWTService() {
    }

    @PostConstruct
    public void init() {
        this.expirationDuration = Duration.parse(expiration);
    }

    public String generateToken(String emailAddress) {
        Map<String, Objects> claims = new HashMap<>();
        Instant now = Instant.now();
        Instant expiryDate = now.plus(expirationDuration);

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(emailAddress)
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiryDate))
                .and()
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        byte[] keyValue = Decoders.BASE64.decode(secKey);
        return Keys.hmacShaKeyFor(keyValue);
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractEmail(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
