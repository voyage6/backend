package com.mini.backend.domain;

import com.mini.backend.dto.PostRequestDto;
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

    @Column
    private String title;

    @Column
    private String contents;

    @Column
    private String category;

//    @Column
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
//        this.imgFileNames = requestDto.getImgFileNames();
        this.user = user;
    }
}
