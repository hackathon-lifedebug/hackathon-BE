package com.example.lifedebug.domain.calendar.service;

import com.example.lifedebug.domain.calendar.dto.CalendarRequest;
import com.example.lifedebug.domain.calendar.dto.CalendarResponse;
import com.example.lifedebug.domain.calendar.entity.Calendar;
import com.example.lifedebug.domain.calendar.mapper.CalendarMapper;
import com.example.lifedebug.domain.calendar.repository.CalendarRepository;
import com.example.lifedebug.domain.user.entity.Mentee;
import com.example.lifedebug.domain.user.entity.Mentor;
import com.example.lifedebug.domain.user.service.MenteeService;
import com.example.lifedebug.domain.user.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;
    private final MentorService mentorService;
    private final MenteeService menteeService;
    private final CalendarMapper calendarMapper;

    @Transactional
    public CalendarResponse saveSchedule(String loginId, CalendarRequest request){
        Mentor mentor = mentorService.findByLoginId(loginId);
        Mentee mentee = menteeService.findById(request.getMenteeId());
        Calendar calendar = calendarMapper.toEntity(request);
        calendar.setMentor(mentor);
        calendar.setMentee(mentee);

        calendarRepository.save(calendar);
        return calendarMapper.toResponseDto(calendar);
    }

    public List<CalendarResponse> findSchedules(String loginId, String role, int year, int month){
        LocalDateTime startOfMonth = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime endOfMonth = startOfMonth.withDayOfMonth(startOfMonth.toLocalDate().lengthOfMonth())
                .withHour(23).withMinute(59).withSecond(59);

        List<Calendar> calendars = new ArrayList<>();
        if (role.equals("MENTOR")) {
            Mentor mentor = mentorService.findByLoginId(loginId);
            calendars = calendarRepository.findAllByMentorAndStartAtBetween(mentor, startOfMonth, endOfMonth);
        } else if (role.equals("MENTEE")) {
            Mentee mentee = menteeService.findByLoginId(loginId);
            calendars = calendarRepository.findAllByMenteeAndStartAtBetween(mentee, startOfMonth, endOfMonth);
        }
        return calendars.stream().map(calendarMapper::toResponseDto).toList();
    }
}
