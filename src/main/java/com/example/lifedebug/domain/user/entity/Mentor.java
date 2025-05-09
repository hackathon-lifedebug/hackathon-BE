package com.example.lifedebug.domain.user.entity;

import com.example.lifedebug.global.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mentor extends BaseEntity implements User {
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

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "mentor_subjects", joinColumns = @JoinColumn(name = "mentor_id"))
    @Column(name = "subject")
    private List<Subject> subjects;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "mentor_languages", joinColumns = @JoinColumn(name = "mentor_id"))
    @Column(name = "language")
    private List<Language> languages;

    private Double rating;

    private Integer reviewCnt;

    private String profileImage;

    private String description;

    private boolean isActive;

    @Override
    public String getRole() {
        return "MENTOR";
    }
}
