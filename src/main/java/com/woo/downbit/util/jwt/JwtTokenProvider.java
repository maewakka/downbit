package com.woo.downbit.util.jwt;


import com.woo.downbit.entity.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.access-token-expire-time}")
    private Long ACCESS_TOKEN_EXPIRE_TIME;

    /**
     * JWT 토큰을 생성하는 메서드, Claim에는 userId가 들어간다.
     * @param userId
     * @return JWT Token
     */
    public String createToken(Long userId) {
        Claims claims = Jwts.claims();
        claims.put("id", userId);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

}
