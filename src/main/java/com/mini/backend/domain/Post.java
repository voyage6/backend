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
    private Long id;//아이디

    @Column(nullable = true)
    private String title;//제목

    @Column(nullable = true)
    private String category;//카테고리

    @Column(nullable = true)
    private String contents;//게시물 내용

    @ElementCollection
    private List<String> imgUrls = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)//여러개의 게시글을 가지기에
    @JoinColumn(name = "userId")
    private Users user;

    public Post(PostRequestDto requestDto, Users user) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.category = requestDto.getCategory();
        this.imgUrls =requestDto.getImgUrls();
//        this.imgFileNames = requestDto.getImgFileNames();
        this.user = user;
    }

    public void update(UpdatePostRequestDto requestDto){
        this.title=requestDto.getTitle();
        this.contents=requestDto.getContents();
        this.category=requestDto.getCategory();
        this.imgUrls=requestDto.getImgUrls();//???
    }
}
