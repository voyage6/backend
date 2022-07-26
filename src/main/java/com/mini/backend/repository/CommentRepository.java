package com.mini.backend.repository;

import com.mini.backend.domain.Comment;
import com.mini.backend.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostOrderByCreatedAtAsc(Post post);
}
