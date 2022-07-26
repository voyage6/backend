package com.mini.backend.service;

import com.mini.backend.domain.Post;
import com.mini.backend.domain.Users;
import com.mini.backend.dto.PostDeleteRequestDto;
import com.mini.backend.dto.PostDetailsResponseDto;
import com.mini.backend.dto.PostRequestDto;
import com.mini.backend.dto.PostResponseDto;
import com.mini.backend.repository.PostRepository;
import com.mini.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public PostResponseDto createPost(PostRequestDto postrequestDto, String userId) {
//        //TODO xss 방지
//        String contentsCheck = requestDto.getContents();
//        String titleCheck = requestDto.getTitle();
//        if (contentsCheck.contains("script") || contentsCheck.contains("<") || contentsCheck.contains(">")) {
//            Post post = new Contents(requestDto, username, "xss 안돼요,,하지마세요ㅠㅠ");
//            ContentsRepository.save(contents);
//            return post;
//        }
//        if (titleCheck.contains("script") || titleCheck.contains("<") || titleCheck.contains(">")) {
//            Contents contents = new Contents("xss 안돼요,,하지마세요ㅠㅠ", username, "xss 안돼요,,하지마세요ㅠㅠ");
//            ContentsRepository.save(contents);
//            return contents;
//        }
        Users user = userRepository.findByUserId(userId)
                .orElseThrow( () -> new IllegalAccessError("해당 유저는 존재하지 않습니다."));
        Post post = new Post(postrequestDto, user);
        postRepository.save(post);
        return new PostResponseDto(post);
    }


    public ResponseEntity<?> deletePost(Long postId, PostDeleteRequestDto requestDto, String userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow( () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        System.out.println(post.getUser().getUserId());
        System.out.println(requestDto.getUserId());
        if(!post.getUser().getUserId().equals(userId)){
            return new ResponseEntity<>(HttpStatus.valueOf(403));
        }else {
            postRepository.deleteById(postId);
            return new ResponseEntity<>(HttpStatus.valueOf(204));
        }
    }


    public PostDetailsResponseDto getPostDetails(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow( () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        return new PostDetailsResponseDto(post);
    }

}
