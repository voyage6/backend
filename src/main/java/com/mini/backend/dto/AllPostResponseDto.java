package com.mini.backend.dto;

import com.mini.backend.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AllPostResponseDto {//응답
    private Long id;
    private String title;
    private Long userId;
    private String contents;
    private String category;
    private List<String> imgeurl;//???
    private List<String> comments;//???

    public AllPostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.userId = post.getUser().getId();
        this.contents = post.getContents();
        this.category = post.getCategory();
        this.imgUrls = post.getImgUrls();
    }
}
