package com.example.lifedebug.domain.calendar.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalendarRequest {
    private Long menteeId;

    private String title;

    private LocalDate date;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime startAt;
}
