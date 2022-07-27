package com.mini.backend.dto;

import com.mini.backend.domain.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String writerName;
    private Long writerId;
    private String profileUrl;
    private String contents;
    private LocalDateTime createdAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
//        this.writerName = comment.getUser().getUserName();
//        this.writerId = comment.getUser().getId();
//        this.profileUrl = comment.getUser().getProfileURL();
        this.contents = comment.getContents();
        this.createdAt = comment.getCreatedAt();
    }
}
