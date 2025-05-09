package com.example.lifedebug.domain.matching.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenteeMatchingRequest {
    private Long menteeId;

    private Long mentorId;
}
