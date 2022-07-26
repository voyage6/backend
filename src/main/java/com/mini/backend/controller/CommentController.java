package com.mini.backend.controller;

import com.mini.backend.domain.Comment;
import com.mini.backend.dto.CommentRequestDto;
import com.mini.backend.security.UserDetailsImpl;
import com.mini.backend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @GetMapping("api/posts/{postId}/comments")
    public List<Comment> getComment(@PathVariable Long postId) {
        return commentService.getComment(postId);
    }

    @PostMapping("api/posts/{postId}/comments")
    public ResponseEntity<?> createComment(@PathVariable Long postId, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String userId = userDetails.getUsername();
        return new ResponseEntity<>(commentService.createComment(postId, requestDto, userId), HttpStatus.valueOf(201));
    }

    @DeleteMapping("api/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String userId = userDetails.getUsername();
        return commentService.deleteComment(commentId, userId);
    }

}
