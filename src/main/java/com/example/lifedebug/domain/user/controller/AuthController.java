package com.example.lifedebug.domain.user.controller;

import com.example.lifedebug.domain.user.dto.*;
import com.example.lifedebug.domain.user.service.AuthService;
import com.example.lifedebug.global.response.ApiResponse;
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
    public ResponseEntity<ApiResponse<Void>> signupMentor(@Valid @RequestBody MentorSignupRequest request){
        authService.signupMentor(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<Void>builder().success(201).message("멘토 회원가입에 성공했습니다.").build());
    }

    @PostMapping("/signup/mentee")
    public ResponseEntity<ApiResponse<Void>> signupMentee(@Valid @RequestBody MenteeSignupRequest request){
        authService.signupMentee(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<Void>builder().success(201).message("멘티 회원가입에 성공했습니다.").build());
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request){
        LoginResponse response = authService.login(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.<LoginResponse>builder().success(200).message("로그인에 성공했습니다.").data(response).build());
    }

    @PostMapping("/reissue")
    public ResponseEntity<ApiResponse<LoginResponse>> reissue(HttpServletRequest request){
        String refreshToken = (String) request.getAttribute("refreshToken");
        LoginResponse response = authService.reissue(refreshToken);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.<LoginResponse>builder().success(200).message("토큰 재발급에 성공했습니다.").data(response).build());
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(@AuthenticationPrincipal CustomUserDetails userDetails){
        authService.logout(userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.<Void>builder().success(200).message("로그아웃에 성공했습니다.").build());
    }
}

