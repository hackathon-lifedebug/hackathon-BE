package com.example.lifedebug.domain.review.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponse {
    private Long id;

    private Long mentorId;

    private String mentorName;

    private Long menteeId;

    private String menteeName;

    private int rating;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
