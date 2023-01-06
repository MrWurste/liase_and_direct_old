package com.dawid_kielbasa.liase_and_direct.config;

import com.dawid_kielbasa.liase_and_direct.interfaces.AppUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.Map;

/**
 * Utility class for AuthFiler class. Here are methods witch AuthFilter uses, exported to second class to mantain readibility of code.
 * Token is generated with secretKey bellow.
 */
@Component
public class FilterUtils {
    private String secretKey = "secret";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getIssuer);
    }
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    public String generateToken(AppUserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails);
    }
    private String createToken(Map<String, Object> claims, AppUserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .claim("authorities", userDetails.getAuthorities())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ TimeUnit.HOURS.toMillis(1)))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }
    public Boolean validateToken(String token, AppUserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
