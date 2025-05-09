package com.example.lifedebug.domain.board.repository;

import com.example.lifedebug.domain.board.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Post, Long > {

    Page<Post> findPageBy(Pageable page);
}
