package com.example.lifedebug.domain.review.dto;

import lombok.*;
import org.springframework.data.domain.Page;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MentorReviewResponse {
    private Long mentorId;

    private String mentorName;

    private Integer reviewCnt;

    private Double rating;

    private Page<ReviewResponse> reviews;
}
