package com.mini.backend.controller;

import com.mini.backend.dto.UpdatePostRequestDto;
import com.mini.backend.dto.AllPostResponseDto;
import com.mini.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;
    @GetMapping("/api/posts")//전체조회
    public List<AllPostResponseDto> getAllPosts(){
        List<AllPostResponseDto> posts= postService.getAllPosts();
        return posts;
    }

    @PatchMapping("/api/posts/{postId}")//수정
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody UpdatePostRequestDto updatePostRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.updatePost(id, updatePostRequestDto);
    }
}
