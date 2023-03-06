package com.woo.downbit.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.woo.downbit.dto.user.LoginResponseDto;
import com.woo.downbit.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public ResponseEntity login(@RequestParam String code) throws JsonProcessingException {
        String jwtToken = userService.login(code);
        LoginResponseDto response = LoginResponseDto.builder().accessToken(jwtToken).build();

        log.info(response.toString());

        return ResponseEntity.ok(response);
    }
}
