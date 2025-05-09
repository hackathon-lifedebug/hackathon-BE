package com.example.lifedebug.domain.user.service;

import com.example.lifedebug.domain.user.entity.Mentee;
import com.example.lifedebug.domain.user.repository.MenteeRepository;
import com.example.lifedebug.global.code.ErrorCode;
import com.example.lifedebug.global.exception.CustomException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenteeService {
    private final MenteeRepository menteeRepository;

    public Mentee findById(Long id){
        return menteeRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.MENTEE_NOT_FOUND));
    }

    public Mentee findByLoginId(String loginId){
        return menteeRepository.findByLoginId(loginId)
                .orElseThrow(() -> new CustomException(ErrorCode.MENTEE_NOT_FOUND));
    }
}
