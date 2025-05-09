package com.example.lifedebug.domain.token.repository;

import com.example.lifedebug.domain.token.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByLoginId(String loginId);
    void deleteByLoginId(String loginId);
}
