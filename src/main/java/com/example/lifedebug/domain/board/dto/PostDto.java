package com.example.lifedebug.domain.board.dto;


import com.example.lifedebug.domain.board.entity.AuthorRole;

public class PostDto {
    public Long userid;
    public AuthorRole authorRole;
    public String title;
    public String content;
    public boolean isPrivate;
}
