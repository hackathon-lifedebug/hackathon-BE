package com.example.lifedebug.domain.board.dto;

import com.example.lifedebug.domain.board.entity.AuthorRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private boolean isPrivate;
    private AuthorRole authorRole;

    private Long menteeId; // 작성자가 멘티일 경우
    private Long mentorId; // 작성자가 멘토일 경우
    private Long boardOwnerId; // 게시판 주인 멘토 ID
}

