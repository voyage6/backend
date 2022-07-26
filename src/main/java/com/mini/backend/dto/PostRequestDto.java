package com.mini.backend.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class PostRequestDto {
    private String title;
    private String contents;
    private String category;
    private String imageUrl;
}
