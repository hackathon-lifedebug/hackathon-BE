package com.example.lifedebug.domain.board.dto;

import com.example.lifedebug.domain.board.entity.AuthorRole;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentResponse {
    private Long id;
    private String content;
    private AuthorRole authorRole;
    private String writerName;
    private String createdAt;
}
