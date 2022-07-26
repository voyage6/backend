package com.mini.backend.dto;

import com.mini.backend.domain.Comment;
import com.mini.backend.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AllPostResponseDto {//응답
    private Long id;
    private String title;
    private String writerName;
    private Long writerId;
    private String contents;
    private String category;
    private List<String> imgUrls;//???
    private List<CommentResponseDto> comments;//???
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AllPostResponseDto(Post post, List<CommentResponseDto> commentResponseDtoList) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.writerName = post.getUser().getUserName();
        this.writerId = post.getUser().getId();
        this.contents = post.getContents();
        this.category = post.getCategory();
        this.imgUrls = post.getImgUrls();
        this.comments = commentResponseDtoList;
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
    }
}
