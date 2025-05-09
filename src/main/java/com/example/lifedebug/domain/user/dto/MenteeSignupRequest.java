package com.example.lifedebug.domain.user.dto;

import com.example.lifedebug.domain.user.entity.City;
import com.example.lifedebug.domain.user.entity.Gender;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenteeSignupRequest {
    private String loginId;

    private String password;

    private String name;

    private String email;

    private String phone;

    private Gender gender;

    private City city;
}
