package com.example.lifedebug.domain.calendar.repository;

import com.example.lifedebug.domain.calendar.entity.Calendar;
import com.example.lifedebug.domain.user.entity.Mentee;
import com.example.lifedebug.domain.user.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    List<Calendar> findAllByMentorAndStartAtBetweenOrderByStartAtAsc(Mentor mentor, LocalDateTime start, LocalDateTime end
    );

    List<Calendar> findAllByMenteeAndStartAtBetweenOrderByStartAtAsc(Mentee mentee, LocalDateTime start, LocalDateTime end
    );
}
