package com.example.lifedebug.domain.user.mapper;

import com.example.lifedebug.domain.user.dto.MenteeResponse;
import com.example.lifedebug.domain.user.entity.Mentee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MenteeMapper {
    MenteeResponse toResponseDto(Mentee mentee);
}
