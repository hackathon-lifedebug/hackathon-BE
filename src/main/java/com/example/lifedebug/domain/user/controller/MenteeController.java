package com.example.lifedebug.domain.user.controller;

import com.example.lifedebug.domain.user.service.MenteeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mentees")
public class MenteeController {
    private final MenteeService menteeService;

}
