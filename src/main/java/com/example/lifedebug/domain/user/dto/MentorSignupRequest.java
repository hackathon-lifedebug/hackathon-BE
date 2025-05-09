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
public class MentorSignupRequest {
    private String loginId;

    private String password;

    private String name;

    private String email;

    private String phone;

    private Gender gender;

    private City city;

    private List<Language> languages;

    private List<Subject> subjects;

    private String profileImage;

    private String description;
}
