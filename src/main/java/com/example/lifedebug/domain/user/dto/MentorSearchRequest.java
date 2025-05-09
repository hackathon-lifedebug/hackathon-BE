package com.example.lifedebug.domain.user.dto;

import com.example.lifedebug.domain.user.entity.City;
import com.example.lifedebug.domain.user.entity.Language;
import com.example.lifedebug.domain.user.entity.Subject;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MentorSearchRequest {
    private String keyword;

    private City city;

    private Subject subject;

    private Double minRating;

    private Language language;
}
