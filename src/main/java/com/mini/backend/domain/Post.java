package com.mini.backend.domain;

import com.mini.backend.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.awt.*;
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

    @ManyToOne
    private List<Image> imgeUrls;//이미지??

    @ManyToOne(fetch = FetchType.LAZY)//여러개의 게시글을 가지기에
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany//여러개의 댓글을 가질 수 있어서????
    private List<Comment> comment;

    public void update(PostRequestDto requestDto){
        this.title=requestDto.getTitle();
        this.contents=requestDto.getContents();
        this.category=requestDto.getCategory();
        this.imgeUrls=responseDto.getImgeurl();//???
    }
}
