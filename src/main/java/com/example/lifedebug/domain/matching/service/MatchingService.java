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
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MatchingService {
    private final MatchingRepository matchingRepository;
    private final MentorService mentorService;
    private final MenteeService menteeService;
    private final MatchingMapper matchingMapper;

    // 매칭 요청 Post
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

    // 멘토의 매칭 승인 or 거절 Put
    @Transactional
    public MentorMatchingResponse respondToMatching(MentorMatchingRequest request){
        Matching matching = matchingRepository.findById(request.getMatchingId())
                .orElseThrow(() -> new EntityNotFoundException("해당 ID의 매칭 내역을 찾을 수 없습니다. ID: " + request.getMatchingId()));
        matching.setStatus(request.getStatus());
        return matchingMapper.toMentorResponse(matching);
    }

    // 멘토의 매칭 (요청) 목록
    public Page<MentorMatchingResponse> findMatchingListForMentor(Long mentorId, Pageable pageable){
        Mentor mentor = mentorService.findById(mentorId);
        return matchingRepository.findByMentor(mentor, pageable)
                .map(matchingMapper::toMentorResponse);
    }

    // 멘티의 매칭 (요청) 목록
    public Page<MenteeMatchingResponse> findMatchingListForMentee(Long menteeId, Pageable pageable){
        Mentee mentee = menteeService.findById(menteeId);
        return matchingRepository.findByMentee(mentee, pageable)
                .map(matchingMapper::toMenteeResponse);
    }
}
