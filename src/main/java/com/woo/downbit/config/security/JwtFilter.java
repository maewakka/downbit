package com.woo.downbit.config.security;

import com.woo.downbit.util.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * JWT Filter 로직
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 로그인 요청 URL에 대해서는 해당 필터를 건너뛴다.
        if(request.getServletPath().equals("/users/login")) {
            filterChain.doFilter(request, response);
        } else {
            String token = getToken(request);

            // 유효한 토큰일 경우
            if(jwtTokenProvider.validateToken(token) && StringUtils.hasText(token)) {
                // Token의 userId 기반으로 Authentication 객체를 생성 후, Security의 Authentication에 등록한다.
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                filterChain.doFilter(request, response);
            }
            // 그렇지 않은 경우는 이 필터를 통과하지 못한다.
            else {
                log.error("유효하지 않은 토큰 값입니다.");

                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        }

    }

    /**
     * Header에서 Access Token 값을 가져와 JWT Token으로 변환해주는 메서드
     * @param request
     * @return JWT Token
     */
    private String getToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        log.info("bearerToken : {}", bearerToken);

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }

        return null;
    }

}
