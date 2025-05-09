package com.example.lifedebug.domain.matching.dto;

import com.example.lifedebug.domain.matching.entity.MatchingStatus;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MentorMatchingRequest {
    private Long matchingId;

    private MatchingStatus status;
}
