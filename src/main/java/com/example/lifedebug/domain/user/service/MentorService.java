package com.example.lifedebug.domain.user.service;

import com.example.lifedebug.domain.user.dto.MentorSearchRequest;
import com.example.lifedebug.domain.user.dto.MentorSearchResponse;
import com.example.lifedebug.domain.user.entity.Mentor;
import com.example.lifedebug.domain.user.repository.MentorRepository;
import com.example.lifedebug.global.code.ErrorCode;
import com.example.lifedebug.global.exception.CustomException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MentorService {
    private final MentorRepository mentorRepository;

    public Mentor findById(Long id){
        return mentorRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.MENTOR_NOT_FOUND));
    }

    public Mentor findByLoginId(String loginId){
        return mentorRepository.findByLoginId(loginId)
                .orElseThrow(() -> new CustomException(ErrorCode.MENTOR_NOT_FOUND));
    }

    public Page<MentorSearchResponse> searchMentors(MentorSearchRequest request, Pageable pageable) {
        log.debug("SearchMentors - keyword: {}, city: {}, subject: {}, language: {}, minRating: {}",
                request.getKeyword(), request.getCity(), request.getSubject(), request.getLanguage(), request.getMinRating());

        Page<Mentor> mentors = mentorRepository.searchMentors(
                request.getKeyword().trim().isEmpty() ? null : request.getKeyword(),
                request.getCity(),
                request.getSubject(),
                request.getLanguage(),
                request.getMinRating(),
                pageable
        );

        return mentors.map(mentor -> MentorSearchResponse.builder()
                .id(mentor.getId())
                .name(mentor.getName())
                .city(mentor.getCity())
                .subjects(mentor.getSubjects())
                .rating(mentor.getRating())
                .reviewCnt(mentor.getReviewCnt())
                .gender(mentor.getGender())
                .profileImage(mentor.getProfileImage())
                .description(mentor.getDescription())
                .languages(mentor.getLanguages())
                .build());
    }
}
