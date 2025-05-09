package com.example.lifedebug.domain.matching.dto;

import com.example.lifedebug.domain.matching.entity.MatchingStatus;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenteeMatchingResponse {
    private Long id;
    private Long mentorId;
    private String mentorName;
    private String mentorProfileImage;
    private MatchingStatus status;
}
