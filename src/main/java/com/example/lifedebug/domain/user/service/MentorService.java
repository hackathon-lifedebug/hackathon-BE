package com.example.lifedebug.domain.user.service;

import com.example.lifedebug.domain.matching.entity.MatchingStatus;
import com.example.lifedebug.domain.matching.repository.MatchingRepository;
import com.example.lifedebug.domain.matching.service.MatchingService;
import com.example.lifedebug.domain.user.dto.MenteeResponse;
import com.example.lifedebug.domain.user.dto.MentorSearchRequest;
import com.example.lifedebug.domain.user.dto.MentorSearchResponse;
import com.example.lifedebug.domain.user.entity.Mentor;
import com.example.lifedebug.domain.user.mapper.MenteeMapper;
import com.example.lifedebug.domain.user.repository.MentorRepository;
import com.example.lifedebug.global.code.ErrorCode;
import com.example.lifedebug.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MentorService {
    private final MentorRepository mentorRepository;
    private final MatchingRepository matchingRepository;
    private final MenteeMapper menteeMapper;

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

    public List<MenteeResponse> findMenteesForMentor(String loginId){
        Mentor mentor = findByLoginId(loginId);
        return matchingRepository.findAcceptedMentees(mentor, MatchingStatus.ACCEPTED)
                .stream().map(menteeMapper::toResponseDto).toList();
    }
}
