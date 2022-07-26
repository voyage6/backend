package com.mini.backend.dto;

import com.mini.backend.domain.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Long userId;
    private Long postId;
    private String contents;
    private String createdAt;

    public CommentResponseDto(Comment comment) {
        this.userId = comment.getUser().getId();
        this.postId = comment.getPost().getId();
        this.contents = comment.getContents();
        this.createdAt = comment.getCreatedAt();
    }
}
