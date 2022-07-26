package com.mini.backend.domain;

import com.mini.backend.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comment extends Timestamped{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    private String contents;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

    public Comment(CommentRequestDto requestDto, Post post, Users user) {
        this.contents = requestDto.getContents();
        this.user = user;
        this.post = post;
    }
}
