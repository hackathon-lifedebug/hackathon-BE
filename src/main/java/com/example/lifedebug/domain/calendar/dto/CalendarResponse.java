package com.example.lifedebug.domain.calendar.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalendarResponse {
    private Long id;

    private String menteeName;

    private String title;

    private String description;

    private LocalDateTime startAt;
}
