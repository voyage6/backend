package com.mini.backend.service;

import com.mini.backend.domain.Post;
import com.mini.backend.dto.AllPostResponseDto;
import com.mini.backend.dto.UpdatePostRequestDto;
import com.mini.backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public List<AllPostResponseDto> getAllPosts() {
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        List<AllPostResponseDto> posts = new ArrayList<>();

        for (Post post : postList) {

            List<Comment> commentList = commentRepository.findAllByPostId(post);
            List<CommentDto> comments = new ArrayList<>();
            for (Comment comment : commentList) {
                CommentDto commentDto = new CommentDto(
                        comment.getId(),
                        comment.getWriterName(),
                        comment.getContents(),
                        comment.getCreatedAt());
                comments.add(commentDto);
            }
            AllPostResponseDto postAllDto = new AllPostResponseDto(post.getId(), post.getUser().getUsername()/*여기 맞나?*/,
                    post.getImgeUrls(), post.getContents(), comments/*post.getComment(commentDto)*/, post.getCreatedAt());
            posts.add(postAllDto);//??? 이게 맞나 모르겠다...???
        }
        //HttpStatus.valueOf(204);
        return posts;//201 어떻게 주죠,,,,,
    }
    @Transactional
    public ResponseEntity<?> updatePost(Long id, UpdatePostRequestDto updatePostRequestDto){
    Post post = postRepository.findById(id).orElseThrow(()-> new IllegalArgumentException());
        if(!post.getUser().getUserId().equals(userId)){
            return new ResponseEntity<>(HttpStatus.valueOf(403));
        }else {
            post.update(updatePostRequestDto);
            return new ResponseEntity<> (postRepository.save(post).getId(), HttpStatus.valueOf(204));
        }
    }
}
