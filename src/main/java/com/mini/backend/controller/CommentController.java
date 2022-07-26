package com.mini.backend.controller;

import com.mini.backend.domain.Comment;
import com.mini.backend.domain.Post;
import com.mini.backend.dto.CommentRequestDto;
import com.mini.backend.service.CommentService;
import com.mini.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CommentController {
    private final PostService postService;
    private final CommentService commentService;


    @PostMapping("api/posts/{postId}/comments")
    public  ResponseEntity<?> saveComment(@PathVariable("postId") Post postId, @AuthenticationPrincipal UserDetailsImpl user, CommentRequestDto commentRequestDto) {
        Post post = postService.getPost(postId);
        commentService.saveComment(post, user, commentRequestDto);
        String result = commentService.saveComment(post, user, commentRequestDto);
        return new ResponseEntity<>(result, HttpStatus.valueOf(201));
    }


    @GetMapping()
    public List<Comment> commentList(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable("postId") Post postId){
        Post post = postService.getPost(postId);
        return commentService.getCommentList(post);
    }



    @DeleteMapping("api/comments/{commentId}")
    @ResponseBody
    public ResponseEntity<?> deleteComment(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable("commentId") Long commentId) {
        commentService.deleteComment(commentId, user);
        String result = commentService.deleteComment(commentId, user);
        return new ResponseEntity<>(result, HttpStatus.valueOf(204));
    }





}