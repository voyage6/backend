package com.mini.backend.repository;

import com.mini.backend.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByUpdatedAtDesc();

}
