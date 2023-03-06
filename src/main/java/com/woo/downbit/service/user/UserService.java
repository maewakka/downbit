package com.woo.downbit.service.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woo.downbit.dto.user.CustomUserDetails;
import com.woo.downbit.dto.user.UserInformResponseDto;
import com.woo.downbit.entity.user.Role;
import com.woo.downbit.entity.user.User;
import com.woo.downbit.repository.user.UserRepository;
import com.woo.downbit.util.error.ErrorCode;
import com.woo.downbit.util.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${kakao.client-id}")
    private String clientId;
    @Value("${kakao.redirect-uri}")
    private String redirectURI;

    /**
     * Front에서 전달받은 code를 바탕으로 User 정보를 저장 후, JWT 토큰을 발급한다.
     * @param code
     * @return JWT Token
     */
    public String login(String code) throws JsonProcessingException {
        log.info("사용자 Access Code is -> {}", code);

        // 전달받은 code로 accessToken을 받아옴
        String accessToken = getAccessToken(code);
        log.info("사용자 Access Token is -> {}", accessToken);

        // accessToken을 기반으로 User 정보를 받아오고 User 정보가 저장되어 있지 않으면 저장한다.
        JsonNode attribute = getKakaoUserInfo(accessToken);
        Long userId = attribute.get("id").asLong();
        if(!userRepository.existsById(userId)) {
            userRepository.save(toEntity(attribute));
        }

        return jwtTokenProvider.createToken(userId);
    }

    public UserInformResponseDto getUserInform(User user) {
        return UserInformResponseDto.builder()
                .nickname(user.getNickname())
                .profileImage(user.getProfileImage())
                .build();
    }


    /**
     * Front에서 전달받은 code 값을 바탕으로 Access Token 값을 받아온다.
     * @param code
     * @return accessToken
     */
    private String getAccessToken(String code) throws JsonProcessingException {

        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("redirect_uri", redirectURI);
        body.add("code", code);

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // HTTP 응답 (JSON) -> 액세스 토큰 파싱
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        return jsonNode.get("access_token").asText();
    }

    /**
     * Access Token을 기반으로 유저 정보를 받아온다.
     * @param accessToken
     * @return User Attribute JSON 정보
     */
    private JsonNode getKakaoUserInfo(String accessToken) throws JsonProcessingException {

        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                kakaoUserInfoRequest,
                String.class
        );

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readTree(responseBody);
    }

    /**
     * Kakao 유저 Attribute를 User Entity로 변환해주는 메서드
     * @param attribute
     * @return User
     */
    private User toEntity(JsonNode attribute) {
        Long id = attribute.get("id").asLong();
        JsonNode kakaoAccount = attribute.get("kakao_account");
        JsonNode profile = kakaoAccount.get("profile");

        String nickname = profile.get("nickname").asText();
        String profileImage = profile.get("thumbnail_image_url").asText();
        String email = kakaoAccount.get("email").asText();

        return User.builder()
                .id(id)
                .nickname(nickname)
                .profileImage(profileImage)
                .email(email)
                .userRole(Role.ROLE_USER)
                .budget(0f)
                .build();
    }
}
