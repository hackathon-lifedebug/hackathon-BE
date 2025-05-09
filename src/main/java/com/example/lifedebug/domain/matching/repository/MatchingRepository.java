package com.example.lifedebug.domain.matching.repository;

import com.example.lifedebug.domain.matching.entity.Matching;
import com.example.lifedebug.domain.user.entity.Mentee;
import com.example.lifedebug.domain.user.entity.Mentor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchingRepository extends JpaRepository<Matching, Long> {
    Page<Matching> findByMentor(Mentor mentor, Pageable pageable);

    Page<Matching> findByMentee(Mentee mentee, Pageable pageable);
}
