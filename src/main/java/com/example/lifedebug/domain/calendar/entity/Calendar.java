package com.example.lifedebug.domain.calendar.entity;

import com.example.lifedebug.domain.mentee.entity.Mentee;
import com.example.lifedebug.domain.mentor.entity.Mentor;
import com.example.lifedebug.global.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Calendar extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id", nullable = false)
    private Mentor mentor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id", nullable = false)
    private Mentee mentee;

    private String title;

    private String description;

    private LocalDateTime startAt;

    private LocalDateTime endAt;
}
