package com.mini.backend.service;

import com.mini.backend.domain.Post;
import com.mini.backend.dto.PostResponseDto;
import com.mini.backend.dto.PostRequestDto;
import com.mini.backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private  final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public List<PostResponseDto> getAllPosts() {
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        List<PostResponseDto> posts = new ArrayList<>();

        for (Post post : postList) {
// 이미지 어떨게 들고와요,,,??? 코멘트 이렇게 들고 오는거 맞나요..... 눙물,,,
            List<Comment> commentList = commentRepository.findAllByPostId(post);
            List<CommentDto> comments = new ArrayList<>();
            for (Comment comment : commentList) {
                CommentDto commentDto = new CommentDto(
                        comment.getwriterId(),
                        comment.getWriterName(),
                        comment.getContents(),
                        comment.getCreatedAt());
                comments.add(commentDto);
            }
            PostResponseDto postAllDto = new PostResponseDto(post.getId(), post.getUser().getUsername(),
                    post.getImgeUrls(), post.getContents(), comments/*post.getComment(commentDto)*/, post.getCreatedAt());
            posts.add(postAllDto);//??? 이게 맞나 모르겠다...???
        }
            return posts;
    }
    @Transactional
    public Long updatePost(Long id, PostRequestDto postRequestDto){
    Post post = postRepository.findById(id).orElseThrow(()-> new IllegalArgumentException());

        post.update(postRequestDto);
        return postRepository.save(post).getId();
    }
}
