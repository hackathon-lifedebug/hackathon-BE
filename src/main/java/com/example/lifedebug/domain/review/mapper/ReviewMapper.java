package com.example.lifedebug.domain.review.mapper;

import com.example.lifedebug.domain.review.dto.ReviewRequest;
import com.example.lifedebug.domain.review.dto.ReviewResponse;
import com.example.lifedebug.domain.review.entity.MentorReview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "mentor", ignore = true)
    @Mapping(target = "mentee", ignore = true)
    MentorReview toEntity(ReviewRequest request);

    @Mapping(source = "mentor.id", target = "mentorId")
    @Mapping(source = "mentor.name", target = "mentorName")
    @Mapping(source = "mentee.id", target = "menteeId")
    @Mapping(source = "mentee.name", target = "menteeName")
    ReviewResponse toResponseDto(MentorReview review);
}
