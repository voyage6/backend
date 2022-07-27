package com.mini.backend.repository;

import com.mini.backend.domain.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByUpdatedAtDesc();
    Slice<Post> findAllByOrderByUpdatedAtAsc(Pageable pageable);

}
