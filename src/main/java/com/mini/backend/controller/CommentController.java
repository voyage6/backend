package com.mini.backend.controller;

import com.mini.backend.dto.CommentRequestDto;
import com.mini.backend.dto.CommentResponseDto;
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

    @PostMapping("api/posts/{postId}/comments")
    public  ResponseEntity<?> saveComment(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl user,
                                          @RequestBody CommentRequestDto commentRequestDto) {
        if(user == null){
            return new ResponseEntity<>(HttpStatus.valueOf(404));
        }
        return new ResponseEntity<>(commentService.saveComment(postId, user, commentRequestDto), HttpStatus.valueOf(201));
    }

    @GetMapping("api/posts/{postId}/comments")
    public List<CommentResponseDto> commentList(@PathVariable Long postId){
        return commentService.getCommentList(postId);
    }

    @DeleteMapping("api/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable Long commentId) {
        return commentService.deleteComment(commentId, user);
    }

}