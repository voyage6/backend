package com.mini.backend.domain;

import com.mini.backend.dto.PostRequestDto;
import com.mini.backend.dto.UpdatePostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Post extends Timestamped{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = true)
    private String title;

    @Column(nullable = true)
    private String category;

    @Column(nullable = true)
    private String contents;

    @ElementCollection
    private List<String> imgUrls = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Users user;

    public Post(PostRequestDto requestDto, Users user) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.category = requestDto.getCategory();
        this.imgUrls =requestDto.getImgUrls();
        this.user = user;
    }

    public void update(UpdatePostRequestDto requestDto){
        this.title=requestDto.getTitle();
        this.contents=requestDto.getContents();
        this.category=requestDto.getCategory();
        this.imgUrls=requestDto.getImgUrls();
    }
}
