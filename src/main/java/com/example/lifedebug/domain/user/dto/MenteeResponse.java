package com.example.lifedebug.domain.user.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenteeResponse {
    private Long id;

    private String name;
}
