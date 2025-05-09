package com.example.lifedebug.domain.matching.mapper;

import com.example.lifedebug.domain.matching.dto.MenteeMatchingResponse;
import com.example.lifedebug.domain.matching.dto.MentorMatchingResponse;
import com.example.lifedebug.domain.matching.entity.Matching;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MatchingMapper {
    @Mapping(source = "mentor.id", target = "mentorId")
    @Mapping(source = "mentor.name", target = "mentorName")
    @Mapping(source = "mentor.profileImage", target = "mentorProfileImage")
    MenteeMatchingResponse toMenteeResponse(Matching matching);

    @Mapping(source = "mentee.id", target = "menteeId")
    @Mapping(source = "mentee.name", target = "menteeName")
    @Mapping(source = "mentee.email", target = "menteeEmail")
    MentorMatchingResponse toMentorResponse(Matching matching);
}
