package com.example.lifedebug.domain.calendar.controller;

import com.example.lifedebug.domain.calendar.dto.CalendarRequest;
import com.example.lifedebug.domain.calendar.dto.CalendarResponse;
import com.example.lifedebug.domain.calendar.service.CalendarService;
import com.example.lifedebug.domain.user.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calendars")
public class CalendarController {
    private final CalendarService calendarService;

    @PostMapping
    public ResponseEntity<CalendarResponse> createSchedule(@AuthenticationPrincipal UserDetails userDetails, CalendarRequest request){
        String loginId = userDetails.getUsername();
        return ResponseEntity.status(HttpStatus.CREATED).body(calendarService.saveSchedule(loginId, request));
    }

    @GetMapping
    public ResponseEntity<List<CalendarResponse>> getSchedules(@AuthenticationPrincipal UserDetails userDetails,
                                                               @RequestParam int year, @RequestParam int month){
        String loginId = userDetails.getUsername();
        String role = null;
        if (userDetails instanceof CustomUserDetails customUserDetails) {
            role = customUserDetails.getRole();
        } else {
            // 예외 처리 또는 기본값 설정
            throw new IllegalStateException("Unexpected userDetails type");
        }
        return ResponseEntity.ok(calendarService.findSchedules(loginId, role, year, month));
    }
}
