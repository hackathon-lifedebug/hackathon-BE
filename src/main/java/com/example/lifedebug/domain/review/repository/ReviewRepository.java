package com.example.lifedebug.domain.review.repository;

import com.example.lifedebug.domain.review.entity.MentorReview;
import com.example.lifedebug.domain.user.entity.Mentor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<MentorReview, Long> {
    Page<MentorReview> findByMentorOrderByCreatedAtDesc(Mentor mentor, Pageable pageable);
}
