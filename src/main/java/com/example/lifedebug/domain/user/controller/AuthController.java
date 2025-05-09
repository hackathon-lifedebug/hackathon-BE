package com.example.lifedebug.domain.user.controller;

import com.example.lifedebug.domain.user.dto.*;
import com.example.lifedebug.domain.user.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup/mentor")
    public ResponseEntity<Void> signupMentor(@Valid @RequestBody MentorSignupRequest request){
        authService.signupMentor(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/signup/mentee")
    public ResponseEntity<Void> signupMentee(@Valid @RequestBody MenteeSignupRequest request){
        authService.signupMentee(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reissue")
    public ResponseEntity<LoginResponse> reissue(HttpServletRequest request){
        String refreshToken = (String) request.getAttribute("refreshToken");
        LoginResponse response = authService.reissue(refreshToken);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@AuthenticationPrincipal CustomUserDetails userDetails){
        authService.logout(userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

