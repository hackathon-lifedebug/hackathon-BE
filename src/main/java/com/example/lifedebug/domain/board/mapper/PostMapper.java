package com.example.lifedebug.domain.board.mapper;

import com.example.lifedebug.domain.board.dto.PostResponse;
import com.example.lifedebug.domain.board.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostResponse toResponse(Post post);
}
