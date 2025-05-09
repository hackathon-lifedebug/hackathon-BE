package com.example.lifedebug.domain.user.service;

import com.example.lifedebug.domain.user.dto.CustomUserDetails;
import com.example.lifedebug.domain.user.repository.MenteeRepository;
import com.example.lifedebug.domain.user.repository.MentorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final MentorRepository mentorRepository;
    private final MenteeRepository menteeRepository;

    public CustomUserDetails loadUserDetailsByLoginId(String loginId) {
        return mentorRepository.findByLoginId(loginId)
                .<CustomUserDetails>map(CustomUserDetails::new)
                .or(() -> menteeRepository.findByLoginId(loginId).map(CustomUserDetails::new))
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));
    }
}
