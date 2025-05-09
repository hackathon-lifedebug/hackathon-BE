package com.example.lifedebug.domain.user.entity;

import com.example.lifedebug.global.util.entity.BaseEntity;
import com.example.lifedebug.global.util.entity.City;
import com.example.lifedebug.global.util.entity.Gender;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mentee extends BaseEntity implements User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loginId;

    private String password;

    private String name;

    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private City city;

    private boolean isActive;

    @Override
    public String getRole() {
        return "MENTEE";
    }
}
