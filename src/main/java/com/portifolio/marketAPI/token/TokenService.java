package com.portifolio.marketAPI.token;

import com.portifolio.marketAPI.entity.Establishment;
import com.portifolio.marketAPI.entity.User;
import com.portifolio.marketAPI.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class TokenService {
    @Value("${jwt.secret}")
    private String SECRET;

    @Value("${jwt.expiration}")
    private Long EXPIRATION_MS;

    public TokenService() {
    }

    public String generateToken(User user, Establishment establishment){
        return Jwts.builder()
                .subject(user.getId())
                .claim("establishment", establishment.getId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(getSigneyKey())
                .compact();
    }

    private SecretKey getSigneyKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigneyKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    public boolean isTokenValid(String token) {
        try {
            return !isTokenExpired(token);
        }catch (Exception e){
            throw new UnauthorizedException("sessão expirada");
        }
    }

    public String extractUserId(String token){
        return extractAllClaims(token).getSubject();
    }

    public String extractEstablishment(String token){
        return extractAllClaims(token).get("establishment", String.class);
    }
}
