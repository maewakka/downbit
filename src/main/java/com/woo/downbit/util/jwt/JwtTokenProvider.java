package com.woo.downbit.util.jwt;


import com.woo.downbit.dto.user.CustomUserDetails;
import com.woo.downbit.entity.user.User;
import com.woo.downbit.service.user.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.access-token-expire-time}")
    private Long ACCESS_TOKEN_EXPIRE_TIME;

    private final CustomUserDetailsService customUserDetailsService;

    /**
     * JWT 토큰을 생성하는 메서드, Claim에는 userId가 들어간다.
     * @param userId
     * @return JWT Token
     */
    public String createToken(Long userId) {
        Claims claims = Jwts.claims();
        claims.put("id", userId.toString());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * JWT 토큰을 바탕으로 Security에서 사용할 Authentication 객체를 반환한다.
     * @param token
     * @return Authentication
     */
    public Authentication getAuthentication(String token) {
        // JWT 토큰에서 id 값을 가져온다.
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        String userId = claims.get("id").toString();

        // userId 값으로 인증을 진행 후 Authentication 객체를 반환한다.
        CustomUserDetails principal = (CustomUserDetails) customUserDetailsService.loadUserByUsername(userId);

        return new UsernamePasswordAuthenticationToken(principal, "", principal.getAuthorities());
    }

    /**
     * 토큰이 유효한지 검사한다. 유효하다면 true, 그렇지 않다면 false를 반환한다.
     * @param token
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
