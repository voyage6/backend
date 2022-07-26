package com.mini.backend.controller;

import com.mini.backend.dto.PostDetailsResponseDto;
import com.mini.backend.dto.PostRequestDto;
import com.mini.backend.dto.UpdatePostRequestDto;
import com.mini.backend.dto.AllPostResponseDto;
import com.mini.backend.security.UserDetailsImpl;
import com.mini.backend.service.PostService;
import com.mini.backend.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;
    private final S3Service s3Service;

    @GetMapping("/api/posts")//전체조회
    public List<AllPostResponseDto> getAllPosts(){
        return postService.getAllPosts();
    }

    @PatchMapping("/api/posts/{postId}")//수정
    public ResponseEntity<?> updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequestDto updatePostRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.updatePost(postId, updatePostRequestDto, userDetails);
    }

    @PostMapping("/api/posts")
    public ResponseEntity<?> createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println(userDetails.getUser().getUserName());
        System.out.println(userDetails.getUsername());
        String userId = userDetails.getUsername();
        return new ResponseEntity<>(postService.createPost(postRequestDto, userId), HttpStatus.valueOf(201));
    }

    @GetMapping("/api/posts/{postId}")
    public PostDetailsResponseDto getPostDetails(@PathVariable Long postId) {
        return postService.getPostDetails(postId);
    }

    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String userId = userDetails.getUsername();
        return postService.deletePost(postId, userId);
    }

    @PostMapping("api/images")
    public ResponseEntity<?> uploadImage(@RequestPart(value = "file", required = false) List<MultipartFile> files) throws IOException {
        List<String> imgUrls = s3Service.upload(files);
        return new ResponseEntity<>(imgUrls, HttpStatus.valueOf(201));
    }

    @DeleteMapping("api/images")
    public ResponseEntity<?> deleteImage(@RequestBody Map<String, String> imgUrlMap){
        String imgUrl = imgUrlMap.get("imgUrl");
        System.out.println(imgUrl);
        s3Service.delete(imgUrl);
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }
}
