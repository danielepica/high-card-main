package it.sara.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JwtService {

    private final String SECRET = "my-super-secret-key-my-super-secret-key";
    private final String ISSUER = "demo-app";

    public String generateTestToken() {

        return Jwts.builder()
                .setSubject("test-user")
                .claim("roles", List.of("USER"))
                .setIssuer(ISSUER)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1h
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims validateToken(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(SECRET.getBytes())
                .requireIssuer(ISSUER)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
