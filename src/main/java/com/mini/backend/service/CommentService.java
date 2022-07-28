package com.mini.backend.service;

import com.mini.backend.domain.Comment;
import com.mini.backend.domain.Post;
import com.mini.backend.dto.CommentRequestDto;
import com.mini.backend.dto.CommentResponseDto;
import com.mini.backend.repository.CommentRepository;
import com.mini.backend.repository.PostRepository;
import com.mini.backend.repository.UserRepository;
import com.mini.backend.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<CommentResponseDto> getCommentList(Long postId) {
        List<Comment> comments = commentRepository.findAllByPostIdOrderByCreatedAtAsc(postId);
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for(Comment comment : comments){
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
            commentResponseDtoList.add(commentResponseDto);
        }
        return commentResponseDtoList;
    }

    public CommentResponseDto saveComment(Long postId, UserDetailsImpl user,CommentRequestDto commentRequestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow( () -> new IllegalArgumentException("존재하지 않는 게시글 입니다."));
        Comment comment = new Comment(post, user, commentRequestDto);
        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    public ResponseEntity<?> deleteComment(Long commentId, UserDetailsImpl user) {
        if (isWriter(commentId, user)) {
            commentRepository.deleteById(commentId);
            return new ResponseEntity<>(HttpStatus.valueOf(204));
        }
        commentRepository.deleteById(commentId);
        return new ResponseEntity<>(HttpStatus.valueOf(204)); //TODO 수정
    }

    private boolean isWriter(Long commentId, UserDetailsImpl user) {
        Comment comment = commentRepository.findById(commentId).get();

        String currentUser = user.getUsername();
        String writer = comment.getUser().getUserId();

        return currentUser.equals(writer);
    }
}
