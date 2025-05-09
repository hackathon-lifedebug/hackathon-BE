package com.example.lifedebug.domain.matching.controller;

import com.example.lifedebug.domain.matching.dto.MenteeMatchingRequest;
import com.example.lifedebug.domain.matching.dto.MenteeMatchingResponse;
import com.example.lifedebug.domain.matching.dto.MentorMatchingRequest;
import com.example.lifedebug.domain.matching.dto.MentorMatchingResponse;
import com.example.lifedebug.domain.matching.service.MatchingService;
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
    public ResponseEntity<MenteeMatchingResponse> requestMatching(@RequestBody MenteeMatchingRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(matchingService.requestMatching(request));
    }

    @PutMapping
    public ResponseEntity<MentorMatchingResponse> respondToMatching(@RequestBody MentorMatchingRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(matchingService.respondToMatching(request));
    }

    @GetMapping("mentor/{mentorId}")
    public ResponseEntity<Page<MentorMatchingResponse>> getMatchingListForMentor(@PathVariable Long mentorId,
                                                                                 @RequestParam(defaultValue = "0") int page,
                                                                                 @RequestParam(defaultValue = "7") int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return ResponseEntity.status(HttpStatus.OK).body(matchingService.findMatchingListForMentor(mentorId, pageable));
    }

    @GetMapping("/{menteeId}")
    public ResponseEntity<Page<MenteeMatchingResponse>> getMatchingListForMentee(@PathVariable Long menteeId,
                                                                                 @RequestParam(defaultValue = "0") int page,
                                                                                 @RequestParam(defaultValue = "7") int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return ResponseEntity.status(HttpStatus.OK).body(matchingService.findMatchingListForMentee(menteeId, pageable));
    }
}
