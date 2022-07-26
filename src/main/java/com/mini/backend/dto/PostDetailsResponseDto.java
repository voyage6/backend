package com.mini.backend.dto;

import com.mini.backend.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostDetailsResponseDto {
    private String title;
    private String writerName;
    private Long writerId;
    private String contents;
    private String category;
    private List<String> imgUrls;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PostDetailsResponseDto(Post post) {
        this.title = post.getTitle();
        this.writerName = post.getUser().getUserName();
        this.writerId = post.getUser().getId();
        this.contents = post.getContents();
        this.category = post.getCategory();
        this.imgUrls = post.getImgUrls();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
    }
}
