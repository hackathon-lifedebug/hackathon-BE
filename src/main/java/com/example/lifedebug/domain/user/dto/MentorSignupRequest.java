package com.example.lifedebug.domain.user.dto;

import com.example.lifedebug.global.util.entity.City;
import com.example.lifedebug.global.util.entity.Gender;
import lombok.*;

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

    private String profileImage;

    private String description;
}
