package com.example.lifedebug.domain.mentee.entity;

import com.example.lifedebug.global.util.entity.BaseEntity;
import com.example.lifedebug.global.util.entity.City;
import com.example.lifedebug.global.util.entity.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mentee extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;

    private String password;

    private String name;

    private String email;

    private String phone;

    private Gender gender;

    private City city;

    private boolean isActive;
}
