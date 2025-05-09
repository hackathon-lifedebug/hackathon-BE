package com.example.lifedebug.domain.user.service;

import com.example.lifedebug.domain.user.entity.Mentor;
import com.example.lifedebug.domain.user.repository.MentorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MentorService {
    private final MentorRepository mentorRepository;

    public Mentor findById(Long id){
        return mentorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 ID의 멘토를 찾을 수 없습니다. ID: " + id));
    }
}
