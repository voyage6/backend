package com.mini.backend.dto;

import com.mini.backend.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private Long userId;
    private String contents;
    private String category;
    private List<String> imgUrls;
//    private List<String> imgFileNames;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.userId = post.getUser().getId();
        this.contents = post.getContents();
        this.category = post.getCategory();
        this.imgUrls = post.getImgUrls();
    }
}
