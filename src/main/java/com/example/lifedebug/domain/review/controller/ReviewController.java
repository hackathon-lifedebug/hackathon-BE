package com.example.lifedebug.domain.review.controller;

import com.example.lifedebug.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    // 리뷰 등록

    // 리뷰 조회
}
