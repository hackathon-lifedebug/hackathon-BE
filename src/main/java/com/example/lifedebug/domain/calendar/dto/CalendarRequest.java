package com.example.lifedebug.domain.calendar.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalendarRequest {
    private Long menteeId;

    private String title;

    private String description;

    private LocalDateTime startAt;
}
