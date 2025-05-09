package com.example.lifedebug.domain.review.service;

import com.example.lifedebug.domain.review.dto.ReviewRequest;
import com.example.lifedebug.domain.review.dto.ReviewResponse;
import com.example.lifedebug.domain.review.entity.MentorReview;
import com.example.lifedebug.domain.review.mapper.ReviewMapper;
import com.example.lifedebug.domain.review.repository.ReviewRepository;
import com.example.lifedebug.domain.user.entity.Mentee;
import com.example.lifedebug.domain.user.entity.Mentor;
import com.example.lifedebug.domain.user.service.MenteeService;
import com.example.lifedebug.domain.user.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MentorService mentorService;
    private final MenteeService menteeService;
    private final ReviewMapper reviewMapper;

    @Transactional
    public ReviewResponse saveMentorReview(String loginId, ReviewRequest request){
        Mentee mentee = menteeService.findByLoginId(loginId);
        Mentor mentor = mentorService.findById(request.getMentorId());

        MentorReview review = reviewMapper.toEntity(request);
        review.setMentor(mentor);
        review.setMentee(mentee);

        reviewRepository.save(review);
        return reviewMapper.toResponseDto(review);
    }

    public Page<ReviewResponse> findMentorReviews(Long mentorId, Pageable pageable){
        Mentor mentor = mentorService.findById(mentorId);

        return reviewRepository.findByMentorOrderByCreatedAtDesc(mentor, pageable)
                .map(reviewMapper::toResponseDto);
    }
}
