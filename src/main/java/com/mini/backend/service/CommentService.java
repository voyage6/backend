package com.mini.backend.service;

import com.mini.backend.domain.Comment;
import com.mini.backend.domain.Post;
import com.mini.backend.dto.CommentRequestDto;
import com.mini.backend.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public List<Comment> getCommentList(Post postId) {
        List<Comment> comments = commentRepository.findAllByPostOrderByCreatedAtAsc(postId);
        return comments;
    }

    public Long getPostId(Long commentId) {
        Long postId = commentRepository.findById(commentId).get().getPost().getPostId();
        return postId;
    }

    public String saveComment(Post post, UserDetailsImpl user, CommentRequestDto commentRequestDto) {
        Comment comment = new Comment(post, user, commentRequestDto);
        commentRepository.save(comment);
        return "작성완료";
    }


    public String deleteComment(Long commentId, UserDetailsImpl user) {
        if (isWriter(commentId, user)) {
            commentRepository.deleteById(commentId);
            return "삭제완료";
        }
        return "댓글을 작성한 유저만 삭제할 수 있습니다.";
    }

    private boolean isWriter(Long commentId, UserDetailsImpl user) {
        Comment comment = commentRepository.findById(commentId).get();

        String currentUser = user.getUserId();
        String writer = comment.getUser().getUserId();

        return currentUser.equals(writer);
    }
}
