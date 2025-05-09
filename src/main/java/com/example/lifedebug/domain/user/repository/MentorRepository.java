package com.example.lifedebug.domain.user.repository;

import com.example.lifedebug.domain.user.entity.Mentee;
import com.example.lifedebug.domain.user.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MentorRepository extends JpaRepository<Mentor, Long> {
    Optional<Mentor> findByLoginId(String loginId);
    boolean existsByLoginId(String loginId);
}
