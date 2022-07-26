package com.mini.backend.domain;

import com.mini.backend.dto.CommentRequestDto;
import com.mini.backend.security.UserDetailsImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@NoArgsConstructor
@Getter
@Entity
@Table(name = "Comment")
public class Comment extends Timestamped{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Users user;

    @Column(nullable = false)
    private String contents;

    public Comment(Post post, UserDetailsImpl user, CommentRequestDto CommentRequestDto) {
        this.post = post;
        this.user = user.getUser();
        this.contents = CommentRequestDto.getContents();
    }

}
