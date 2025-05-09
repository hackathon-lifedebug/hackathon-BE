package com.example.lifedebug.domain.board.dto;

import com.example.lifedebug.domain.board.entity.AuthorRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private Long postId;
    private AuthorRole authorRole;
    private String content;
    private Long mentorId; // 작성자 mentor
    private Long menteeId; // 작성자 mentee
}
