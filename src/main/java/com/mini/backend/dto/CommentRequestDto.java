package com.mini.backend.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String contents;

    public CommentRequestDto(String content){
        this.contents = contents;
    }
}
