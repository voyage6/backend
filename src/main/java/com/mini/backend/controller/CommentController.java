package com.mini.backend.controller;

import com.mini.backend.domain.Comment;
import com.mini.backend.domain.Post;
import com.mini.backend.dto.CommentRequestDto;
import com.mini.backend.dto.CommentResponseDto;
import com.mini.backend.security.UserDetailsImpl;
import com.mini.backend.service.CommentService;
import com.mini.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CommentController {
    private final CommentService commentService;


    @PostMapping("api/posts/{postId}/comments")
    public  ResponseEntity<?> saveComment(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl user,
                                          @RequestBody CommentRequestDto commentRequestDto) {
        return new ResponseEntity<>(commentService.saveComment(postId, user, commentRequestDto), HttpStatus.valueOf(201));
    }

    @GetMapping("api/posts/{postId}/comments")
    public List<CommentResponseDto> commentList(@PathVariable Long postId){
        return commentService.getCommentList(postId);
    }

    @DeleteMapping("api/comments/{commentId}")
    @ResponseBody
    public ResponseEntity<?> deleteComment(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable Long commentId) {
        commentService.deleteComment(commentId, user);
        String result = commentService.deleteComment(commentId, user);
        return new ResponseEntity<>(result, HttpStatus.valueOf(204));
    }

}