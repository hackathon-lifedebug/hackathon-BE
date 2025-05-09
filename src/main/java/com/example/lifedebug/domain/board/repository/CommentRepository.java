package com.example.lifedebug.domain.board.repository;

import com.example.lifedebug.domain.board.entity.Comment;
import com.example.lifedebug.domain.board.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long > {

    Page<Post> findPageBy(Pageable page);
    Page<Comment> findByPostId(Long postId, Pageable pageable);

}


