package com.example.lifedebug.domain.calendar.mapper;

import com.example.lifedebug.domain.calendar.dto.CalendarRequest;
import com.example.lifedebug.domain.calendar.dto.CalendarResponse;
import com.example.lifedebug.domain.calendar.entity.Calendar;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CalendarMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "mentor", ignore = true)
    @Mapping(target = "mentee", ignore = true)
    Calendar toEntity(CalendarRequest request);

    @Mapping(source = "mentee.name", target = "menteeName")
    CalendarResponse toResponseDto(Calendar calendar);
}
