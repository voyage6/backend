package com.mini.backend.service;

import com.mini.backend.domain.Comment;
import com.mini.backend.domain.Post;
import com.mini.backend.dto.*;
import com.mini.backend.repository.CommentRepository;
import com.mini.backend.repository.PostRepository;
import com.mini.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public List<AllPostResponseDto> getAllPosts() {
        List<Post> postList = postRepository.findAllByOrderByUpdatedAtDesc();
        List<AllPostResponseDto> posts = new ArrayList<>();

        for (Post post : postList) {

            List<Comment> commentList = commentRepository.findAllByPostIdOrderByCreatedAtAsc(post.getId());
            List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
            for (Comment comment : commentList) {
                CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
                commentResponseDtoList.add(commentResponseDto);
            }
            AllPostResponseDto postAllDto = new AllPostResponseDto(post, commentResponseDtoList);
            posts.add(postAllDto);
        }
        return posts;
    }

    public Slice<Post> getAllPostss(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
//        List<AllPostResponseDto> posts = new ArrayList<>();
//        for (Post post : postList) {
//
//            List<Comment> commentList = commentRepository.findAllByPostIdOrderByCreatedAtAsc(post.getId());
//            List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
//            for (Comment comment : commentList) {
//                CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
//                commentResponseDtoList.add(commentResponseDto);
//            }
//            AllPostResponseDto postAllDto = new AllPostResponseDto(post, commentResponseDtoList);
//            posts.add(postAllDto);
//        }
        return postRepository.findAllByOrderByUpdatedAtAsc(pageable);
    }

    public ResponseEntity<?> updatePost(Long id, UpdatePostRequestDto updatePostRequestDto/*, UserDetailsImpl userDetails*/) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
//        if(!post.getUser().getUserId().equals(userDetails.getUsername())){
//            return new ResponseEntity<>(HttpStatus.valueOf(403));
//        }else {
        post.update(updatePostRequestDto);
        return new ResponseEntity<>(postRepository.save(post).getId(), HttpStatus.valueOf(204));
//    }
    }

    public PostResponseDto createPost(PostRequestDto postrequestDto/*, String userId*/) {
//        Users user = userRepository.findByUserId(userId)
//                .orElseThrow( () -> new IllegalAccessError("해당 유저는 존재하지 않습니다."));
        Post post = new Post(postrequestDto/*, user*/);
        postRepository.save(post);
        return new PostResponseDto(post);
    }


    public ResponseEntity<?> deletePost(Long postId/*, String userId*/) {
        Post post = postRepository.findById(postId)
                .orElseThrow( () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
//        if(!post.getUser().getUserId().equals(userId)){
//            return new ResponseEntity<>(HttpStatus.valueOf(403));
//        }else {
            postRepository.deleteById(postId);
            return new ResponseEntity<>(HttpStatus.valueOf(204));
//        }
    }

    public PostDetailsResponseDto getPostDetails(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow( () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        System.out.println(post.getContents());
        List<Comment> comments = commentRepository.findAllByPostIdOrderByCreatedAtAsc(postId);
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for(Comment comment : comments){
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
            commentResponseDtoList.add(commentResponseDto);
        }
        return new PostDetailsResponseDto(post, commentResponseDtoList);
    }
}
