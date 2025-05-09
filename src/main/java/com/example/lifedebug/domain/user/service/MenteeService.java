package com.example.lifedebug.domain.user.service;

import com.example.lifedebug.domain.user.entity.Mentee;
import com.example.lifedebug.domain.user.repository.MenteeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenteeService {
    private final MenteeRepository menteeRepository;

    public Mentee findById(Long id){
        return menteeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID의 멘티를 찾을 수 없습니다. ID: " + id));
    }

    public Mentee findByLoginId(String loginId){
        return menteeRepository.findByLoginId(loginId)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID의 멘티를 찾을 수 없습니다. ID: " + loginId));
    }
}
