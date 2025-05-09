package com.example.lifedebug.domain.matching.repository;

import com.example.lifedebug.domain.matching.entity.Matching;
import com.example.lifedebug.domain.matching.entity.MatchingStatus;
import com.example.lifedebug.domain.user.entity.Mentee;
import com.example.lifedebug.domain.user.entity.Mentor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchingRepository extends JpaRepository<Matching, Long> {
    Page<Matching> findByMentor(Mentor mentor, Pageable pageable);

    Page<Matching> findByMentee(Mentee mentee, Pageable pageable);

    @Query("""
        select mc.mentee
        from Matching mc
        where mc.mentor = :mentor
          and mc.status = :status
    """)
    List<Mentee> findAcceptedMentees(@Param("mentor") Mentor mentor,
                                     @Param("status") MatchingStatus status);
}
