package com.mini.backend.service;

import com.mini.backend.domain.Comment;
import com.mini.backend.domain.Post;
import com.mini.backend.domain.Users;
import com.mini.backend.dto.CommentRequestDto;
import com.mini.backend.dto.CommentResponseDto;
import com.mini.backend.repository.CommentRepository;
import com.mini.backend.repository.PostRepository;
import com.mini.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<Comment> getComment(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public CommentResponseDto createComment(Long postId, CommentRequestDto requestDto, String userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow( () -> new IllegalArgumentException("해당 게시글은 존재하지 않습니다."));
        Users user = userRepository.findByUserName(userId)
                .orElseThrow( () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다."));
        Comment comment = new Comment(requestDto, post, user);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    public ResponseEntity<?> deleteComment(Long commentId, String userId) {
        //TODO : 유저네임 다르면 403?
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow( () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다."));
        if(comment.getUser().getUserId() != userId){
            return new ResponseEntity<>(HttpStatus.valueOf(403));
        }
        commentRepository.deleteById(commentId);
        return new ResponseEntity<>(HttpStatus.valueOf(204));
    }
}
