package com.example.lifedebug.domain.review.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
    private Long mentorId;

    private int rating;

    private String content;
}
