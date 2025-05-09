package com.example.lifedebug.domain.matching.service;

import com.example.lifedebug.domain.matching.dto.MenteeMatchingRequest;
import com.example.lifedebug.domain.matching.dto.MenteeMatchingResponse;
import com.example.lifedebug.domain.matching.dto.MentorMatchingRequest;
import com.example.lifedebug.domain.matching.dto.MentorMatchingResponse;
import com.example.lifedebug.domain.matching.entity.Matching;
import com.example.lifedebug.domain.matching.entity.MatchingStatus;
import com.example.lifedebug.domain.matching.mapper.MatchingMapper;
import com.example.lifedebug.domain.matching.repository.MatchingRepository;
import com.example.lifedebug.domain.user.entity.Mentee;
import com.example.lifedebug.domain.user.entity.Mentor;
import com.example.lifedebug.domain.user.service.MenteeService;
import com.example.lifedebug.domain.user.service.MentorService;
import com.example.lifedebug.global.code.ErrorCode;
import com.example.lifedebug.global.exception.CustomException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchingService {
    private final MatchingRepository matchingRepository;
    private final MentorService mentorService;
    private final MenteeService menteeService;
    private final MatchingMapper matchingMapper;

    @Transactional
    public MenteeMatchingResponse requestMatching(MenteeMatchingRequest request){
        Mentee mentee = menteeService.findById(request.getMenteeId());
        Mentor mentor = mentorService.findById(request.getMentorId());
        Matching matching = Matching
                .builder()
                .mentee(mentee)
                .mentor(mentor)
                .status(MatchingStatus.REQUESTED)
                .build();
        matchingRepository.save(matching);
        return matchingMapper.toMenteeResponse(matching);
    }

    @Transactional
    public MentorMatchingResponse respondToMatching(MentorMatchingRequest request){
        Matching matching = matchingRepository.findById(request.getMatchingId())
                .orElseThrow(() -> new CustomException(ErrorCode.MATCHING_NOT_FOUND));
        matching.setStatus(request.getStatus());
        return matchingMapper.toMentorResponse(matching);
    }

    public Page<MentorMatchingResponse> findMatchingListForMentor(Long mentorId, Pageable pageable){
        Mentor mentor = mentorService.findById(mentorId);
        return matchingRepository.findByMentor(mentor, pageable)
                .map(matchingMapper::toMentorResponse);
    }

    public Page<MenteeMatchingResponse> findMatchingListForMentee(Long menteeId, Pageable pageable){
        Mentee mentee = menteeService.findById(menteeId);
        return matchingRepository.findByMentee(mentee, pageable)
                .map(matchingMapper::toMenteeResponse);
    }
}
