package com.example.lifedebug.domain.user.repository;

import com.example.lifedebug.domain.user.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MentorRepository extends JpaRepository<Mentor, Long> {
    Optional<Mentor> findByLoginId(String loginId);
    boolean existsByLoginId(String loginId);

    @Query("SELECT m FROM Mentor m " +
            "WHERE m.isActive = true " +
            "AND (:keyword IS NULL OR COALESCE(LOWER(TRIM(m.name)), '') LIKE LOWER(CONCAT('%', TRIM(:keyword), '%'))) " +
            "AND (:city IS NULL OR m.city = :city) " +
            "AND (:subject IS NULL OR :subject IN (SELECT s FROM m.subjects s)) " +
            "AND (:language IS NULL OR :language IN (SELECT l FROM m.languages l)) " +
            "AND (:minRating IS NULL OR " +
            "    (:minRating = 2.5 AND (m.rating IS NOT NULL AND m.rating < 3.0)) OR " +
            "    (:minRating != 2.5 AND m.rating >= :minRating)) " +
            "ORDER BY m.rating DESC NULLS LAST")
    Page<Mentor> searchMentors(@Param("keyword") String keyword,
                               @Param("city") City city,
                               @Param("subject") Subject subject,
                               @Param("language") Language language,
                               @Param("minRating") Double minRating,
                               Pageable pageable);
}
