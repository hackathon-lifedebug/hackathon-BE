package com.example.lifedebug.domain.user.service;

import com.example.lifedebug.domain.user.dto.CustomUserDetails;
import com.example.lifedebug.domain.user.entity.Mentee;
import com.example.lifedebug.domain.user.entity.Mentor;
import com.example.lifedebug.domain.user.repository.MenteeRepository;
import com.example.lifedebug.domain.user.repository.MentorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MentorRepository mentorRepository;
    private final MenteeRepository menteeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 멘토 탐색
        Optional<Mentor> mentor = mentorRepository.findByLoginId(username);
        if (mentor.isPresent()) {
            return new CustomUserDetails(mentor.get());
        }

        // 멘티 탐색
        Optional<Mentee> mentee = menteeRepository.findByLoginId(username);
        if (mentee.isPresent()) {
            return new CustomUserDetails(mentee.get());
        }

        throw new UsernameNotFoundException("해당 로그인 ID로 사용자를 찾을 수 없습니다.");
    }
}
