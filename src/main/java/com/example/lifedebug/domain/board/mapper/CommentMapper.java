package com.example.lifedebug.domain.board.mapper;

import com.example.lifedebug.domain.board.dto.CommentResponse;
import com.example.lifedebug.domain.board.dto.PostResponse;
import com.example.lifedebug.domain.board.entity.Comment;
import com.example.lifedebug.domain.board.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    /*
    @Mapping(target = "writerName", expression =
            "java(comment.getAuthorRole() == AuthorRole.MENTOR ? comment.getMentor().getName() : comment.getMentee().getName())")
    @Mapping(target = "createdAt", expression =
            "java(comment.getCreatedAt().toString())")

     */
    CommentResponse toResponse(Comment comment);
}

