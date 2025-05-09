package com.example.lifedebug.domain.matching.controller;

import com.example.lifedebug.domain.matching.dto.MenteeMatchingRequest;
import com.example.lifedebug.domain.matching.dto.MenteeMatchingResponse;
import com.example.lifedebug.domain.matching.dto.MentorMatchingRequest;
import com.example.lifedebug.domain.matching.dto.MentorMatchingResponse;
import com.example.lifedebug.domain.matching.service.MatchingService;
import com.example.lifedebug.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/matching")
public class MatchingController {
    private final MatchingService matchingService;

    @PostMapping
    public ResponseEntity<ApiResponse<MenteeMatchingResponse>> requestMatching(@RequestBody MenteeMatchingRequest request){
        MenteeMatchingResponse response = matchingService.requestMatching(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<MenteeMatchingResponse>builder().success(201).message("멘토 매칭 요청에 성공했습니다.").data(response).build());
    }

    @PutMapping
    public ResponseEntity<ApiResponse<MentorMatchingResponse>> respondToMatching(@RequestBody MentorMatchingRequest request){
        MentorMatchingResponse response = matchingService.respondToMatching(request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.<MentorMatchingResponse>builder().success(200).message("멘토 매칭 응답에 성공했습니다.").data(response).build());
    }

    @GetMapping("mentor/{mentorId}")
    public ResponseEntity<ApiResponse<Page<MentorMatchingResponse>>> getMatchingListForMentor(@PathVariable Long mentorId,
                                                                                 @RequestParam(defaultValue = "0") int page,
                                                                                 @RequestParam(defaultValue = "7") int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<MentorMatchingResponse> responses = matchingService.findMatchingListForMentor(mentorId, pageable);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.<Page<MentorMatchingResponse>>builder().success(200).message("멘토의 매칭 리스트 조회에 성공했습니다.").data(responses).build());
    }

    @GetMapping("/{menteeId}")
    public ResponseEntity<ApiResponse<Page<MenteeMatchingResponse>>> getMatchingListForMentee(@PathVariable Long menteeId,
                                                                                 @RequestParam(defaultValue = "0") int page,
                                                                                 @RequestParam(defaultValue = "7") int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<MenteeMatchingResponse> responses = matchingService.findMatchingListForMentee(menteeId, pageable);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.<Page<MenteeMatchingResponse>>builder().success(200).message("멘티의 매칭 리스트 조회에 성공했습니다.").data(responses).build());
    }
}
