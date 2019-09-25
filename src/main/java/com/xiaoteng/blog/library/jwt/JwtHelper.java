package com.xiaoteng.blog.library.jwt;

import com.xiaoteng.blog.config.JwtConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtHelper {

    @Autowired
    private JwtConfig jwtConfig;

    public String generateUserJwt(String id) {
        return Jwts.builder()
                .setSubject(id)
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpire() * 1000))
                .signWith(getKey())
                .compact();
    }

    public String parseUserToken(String token) {
        return Jwts.parser().setSigningKey(getKey()).parseClaimsJws(token).getBody().getSubject();
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes());
    }

}
