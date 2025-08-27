package com.yusufmendes.jwtandsecurity.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    public static final String SECRET_KEY = "omzWtkzOV29Jbys5FT4KByM3H+PIshkFKnz/xi7pXj8=";

    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("role", "ADMIN");

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .addClaims(claimsMap)
                .setIssuedAt(new Date()) //token oluşturulma zamanı
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2)) //token bitme süresi
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /*public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5dXN1Zm1lbmRlcyIsInJvbGUiOiJBRE1JTiIsImlhdCI6MTc1NjMwNjY2MSwiZXhwIjoxNzU2MzEzODYxfQ.JbagI_uNIQZUlGfnPdsDIeSZgkGBdbfpSjKFXrX4FMU";
        String key = "role";
        Object value = getClaimsByKey(token, key);
        System.out.println("value: " + value);
    }*/

    //claims içinden değer çekme
    public Object getClaimsByKey(String token, String key) {
        Claims claims = getClaims(token);
        return claims.get(key);
    }

    public Claims getClaims(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    //token çözme metodu
    public <T> T exportToken(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = getClaims(token);
        return claimsTFunction.apply(claims);
    }

    //token içinden username alma fonksiyonu
    public String getUsernameByToken(String token) {
        return exportToken(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token) {
        Date expiredDate = exportToken(token, Claims::getExpiration);
        return new Date().before(expiredDate);
    }


    public Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
