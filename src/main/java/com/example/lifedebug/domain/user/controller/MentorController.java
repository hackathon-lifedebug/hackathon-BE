package com.example.lifedebug.domain.user.controller;

import com.example.lifedebug.domain.user.dto.MentorSearchRequest;
import com.example.lifedebug.domain.user.dto.MentorSearchResponse;
import com.example.lifedebug.domain.user.service.MentorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mentors")
public class MentorController {
    private final MentorService mentorService;

    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @PostMapping("/search")
    public ResponseEntity<Page<MentorSearchResponse>> searchMentors(
            @RequestBody MentorSearchRequest request,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "6") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("rating").descending());
        Page<MentorSearchResponse> result = mentorService.searchMentors(request, pageable);
        return ResponseEntity.ok(result);
    }
}
