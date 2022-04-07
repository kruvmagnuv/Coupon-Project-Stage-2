package com.smrt.CouponProject.jwt;

import com.smrt.CouponProject.beans.UserDetails;
import com.smrt.CouponProject.exceptions.JwtException;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTUtils {
    // Our signature algorithm
    private String signatureAlgorithm = SignatureAlgorithm.HS256.getJcaName();
    // Our secret key
    private String encodedSecretKey = "AbCdEfGhIjKlMnOpQrStUvWxYz12345678901234567890";
    // Our key used the signature algorithm and secret key from above
    private Key decodedSecretKey = new SecretKeySpec(Base64.getDecoder().decode(encodedSecretKey), this.signatureAlgorithm);

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userDetails.getId());
        claims.put("role", userDetails.getRole());
        return createToken(claims, userDetails.getEmail());
    }

    private String createToken(Map<String, Object> claims, String email) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .signWith(this.decodedSecretKey)
                .compact();
    }

    private Claims extractAllClaims(String token) throws ExpiredJwtException {
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(this.decodedSecretKey)
                .build();
        return jwtParser.parseClaimsJws(token).getBody();
    }

    private String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    private Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    private boolean isTokenExpired(String token) {
        try {
            extractAllClaims(token);
            return false;
        } catch (ExpiredJwtException err) {
            return true;
        }
    }

    public UserDetails validateToken(String token) throws JwtException {
        if (isTokenExpired(token)) {
            throw new JwtException("Token is invalid");
        }
        Claims claims = extractAllClaims(token);
        return new UserDetails(claims.getSubject(), "*******", claims.get("role", String.class), claims.get("id", Integer.class));
    }
}