package com.mini.backend.repository;

import com.mini.backend.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
//    List<Post> findAllByCreatedAtOrderByCreatedAtDesc(LocalDateTime start, LocalDateTime end);
}
