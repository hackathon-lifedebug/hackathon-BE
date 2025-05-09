package com.example.lifedebug.domain.user.dto;

import com.example.lifedebug.domain.user.entity.City;
import com.example.lifedebug.domain.user.entity.Gender;
import com.example.lifedebug.domain.user.entity.Language;
import com.example.lifedebug.domain.user.entity.Subject;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MentorSearchResponse {
    private Long id;

    private String name;

    private City city;

    private List<Subject> subjects;

    private Double rating;

    private Integer reviewCnt;

    private Gender gender;

    private String profileImage;

    private String description;

    private List<Language> languages;
}
