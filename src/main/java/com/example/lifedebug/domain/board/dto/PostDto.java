package com.example.lifedebug.domain.board.dto;


import com.example.lifedebug.domain.board.entity.AuthorRole;
import com.example.lifedebug.domain.user.entity.Mentor;

import java.util.List;

public class PostDto {
    public Long mentorId;
    public String title;
    public String content;
    public boolean isPrivate;
}
