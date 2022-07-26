package com.mini.backend.domain;

import com.mini.backend.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@NoArgsConstructor
@Getter
@Entity
@Table(name = "Comment")
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "postId", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User userId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contents;

    public Comment(Post post, UserDetailsImpl user, CommentRequestDto CommentRequestDto) {
        this.post = post;
        this.userId = user.getUser();
        this.contents = CommentRequestDto.getContents();
    }

}
