package com.mini.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String writerName;
    private String contents;
    private String category;
    private List<ImgeDto> imgeurl= new ArrayList<>();//???
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentDto> comments= new ArrayList<>();
}
