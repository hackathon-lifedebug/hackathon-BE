
package com.example.lifedebug.domain.user.service;

import com.example.lifedebug.domain.token.entity.RefreshToken;
import com.example.lifedebug.domain.token.repository.TokenRepository;
import com.example.lifedebug.domain.token.service.TokenService;
import com.example.lifedebug.domain.user.dto.*;
import com.example.lifedebug.domain.user.entity.Mentee;
import com.example.lifedebug.domain.user.entity.Mentor;
import com.example.lifedebug.domain.user.entity.User;
import com.example.lifedebug.domain.user.repository.MenteeRepository;
import com.example.lifedebug.domain.user.repository.MentorRepository;
import com.example.lifedebug.global.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MentorRepository mentorRepository;
    private final MenteeRepository menteeRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final TokenService tokenService;
    private final UserService userService;

    public void signupMentor(MentorSignupRequest request) {
        if (mentorRepository.existsByLoginId(request.getLoginId()) || menteeRepository.existsByLoginId(request.getLoginId())) {
            throw new IllegalArgumentException("이미 존재하는 ID입니다.");
        }

        Mentor mentor = Mentor.builder()
                .loginId(request.getLoginId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .gender(request.getGender())
                .city(request.getCity())
                .languages(request.getLanguages())
                .subjects(request.getSubjects())
                .profileImage(request.getProfileImage())
                .description(request.getDescription())
                .isActive(true)
                .build();

        mentorRepository.save(mentor);
    }

    public void signupMentee(MenteeSignupRequest request) {
        if (menteeRepository.existsByLoginId(request.getLoginId()) || menteeRepository.existsByLoginId(request.getLoginId())) {
            throw new IllegalArgumentException("이미 존재하는 ID입니다.");
        }

        Mentee mentee = Mentee.builder()
                .loginId(request.getLoginId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .gender(request.getGender())
                .city(request.getCity())
                .isActive(true)
                .build();

        menteeRepository.save(mentee);
    }

    public LoginResponse login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getLoginId(), request.getPassword())
            );

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            String accessToken = jwtUtil.createAccessToken(userDetails);
            String refreshToken = jwtUtil.createRefreshToken(userDetails);

            tokenService.saveRefreshToken(userDetails.getUsername(), refreshToken);

            return new LoginResponse(accessToken, refreshToken);
        } catch (BadCredentialsException e) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 잘못되었습니다.");
        }
    }

    public LoginResponse reissue(String refreshToken) {
        if (refreshToken == null) {
            throw new IllegalArgumentException("유효하지 않은 리프레시 토큰입니다.");
        }

        refreshToken = refreshToken.replace("Bearer ", "");

        if (!jwtUtil.isValid(refreshToken) || !"refresh".equals(jwtUtil.getCategory(refreshToken))) {
            throw new IllegalArgumentException("유효하지 않은 리프레시 토큰입니다.");
        }

        String loginId = jwtUtil.getSubject(refreshToken);

        String savedToken = tokenService.getRefreshToken(loginId);
        if(savedToken == null){
            throw new IllegalArgumentException("리프레시 토큰이 일치하지 않습니다.");
        }

        CustomUserDetails userDetails = userService.loadUserDetailsByLoginId(loginId);

        String newAccessToken = jwtUtil.createAccessToken(userDetails);
        String newRefreshToken = jwtUtil.createRefreshToken(userDetails);

        tokenService.saveRefreshToken(loginId, newRefreshToken);

        return new LoginResponse(newAccessToken, newRefreshToken);
    }

    public void logout(String loginId) {
        tokenService.deleteRefreshToken(loginId);
    }
}

