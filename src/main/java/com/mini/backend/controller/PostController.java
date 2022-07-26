package com.mini.backend.controller;

import com.mini.backend.dto.PostRequestDto;
import com.mini.backend.dto.PostResponseDto;
import com.mini.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;
    @GetMapping("/api/posts")//전체조회
    public List<PostResponseDto> getAllPosts(){
        List<PostResponseDto> posts= postService.getAllPosts();
        return posts;
    }

    @PatchMapping("/api/posts/{postId}")//수정
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.updatePost(id, postRequestDto);
    }
}
