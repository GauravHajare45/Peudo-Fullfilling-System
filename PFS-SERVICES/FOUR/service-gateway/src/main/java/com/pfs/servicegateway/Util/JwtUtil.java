package com.pfs.servicegateway.Util;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

@Component
public class JwtUtil {


    private final String SECRET_KEY = "5367566B59703373367639792F423F4528482B4D6251655468576D5A713474";


    public void validateToken(final String token) {
        System.out.println(token);
        Jwts.parserBuilder().build().parseClaimsJws(token);
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

