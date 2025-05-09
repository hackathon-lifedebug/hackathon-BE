package com.example.lifedebug.domain.user.repository;

import com.example.lifedebug.domain.user.entity.Mentee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenteeRepository extends JpaRepository<Mentee, Long> {
    Optional<Mentee> findByLoginId(String loginId);
    boolean existsByLoginId(String loginId);
}
