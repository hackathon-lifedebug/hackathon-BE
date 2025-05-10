package com.example.lifedebug.domain.calendar.controller;

import com.example.lifedebug.domain.calendar.dto.CalendarRequest;
import com.example.lifedebug.domain.calendar.dto.CalendarResponse;
import com.example.lifedebug.domain.calendar.service.CalendarService;
import com.example.lifedebug.domain.user.dto.CustomUserDetails;
import com.example.lifedebug.global.response.ApiResponse;
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
    public ResponseEntity<ApiResponse<CalendarResponse>> createSchedule(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CalendarRequest request){
        String loginId = userDetails.getUsername();
        CalendarResponse response = calendarService.saveSchedule(loginId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<CalendarResponse>builder().success(201).message("캘린더 일정 등록에 성공했습니다.").data(response).build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CalendarResponse>>> getSchedules(@AuthenticationPrincipal UserDetails userDetails,
                                                               @RequestParam int year, @RequestParam int month){
        String loginId = userDetails.getUsername();
        List<CalendarResponse> response = calendarService.findSchedules(loginId, year, month);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.<List<CalendarResponse>>builder().success(200).message("캘린더 월별 조회에 성공했습니다.").data(response).build());
    }
}
