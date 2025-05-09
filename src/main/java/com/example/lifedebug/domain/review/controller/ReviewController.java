package com.example.lifedebug.domain.review.controller;

import com.example.lifedebug.domain.review.dto.ReviewRequest;
import com.example.lifedebug.domain.review.dto.ReviewResponse;
import com.example.lifedebug.domain.review.service.ReviewService;
import com.example.lifedebug.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    @PostMapping
    public ResponseEntity<ApiResponse<ReviewResponse>> createMentorReview(@AuthenticationPrincipal UserDetails userDetails,
                                                                          @RequestBody ReviewRequest request){
        String loginId = userDetails.getUsername();
        ReviewResponse response = reviewService.saveMentorReview(loginId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<ReviewResponse>builder().success(201).message("멘토 리뷰 등록에 성공했습니다.").data(response).build());
    }

    @GetMapping("/{mentorId}")
    public ResponseEntity<ApiResponse<Page<ReviewResponse>>> getMentorReviews(@PathVariable Long mentorId,
                                                                              @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "7") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<ReviewResponse> response = reviewService.findMentorReviews(mentorId, pageable);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.<Page<ReviewResponse>>builder().success(200).message("멘토 리뷰 조회에 성공했습니다.").data(response).build());
    }
}
