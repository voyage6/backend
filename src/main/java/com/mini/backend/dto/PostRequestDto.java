package com.mini.backend.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PostRequestDto {
    private String title;
    private String contents;
    private String category;
    private List<String> imgUrls;
//    private List<String> imgFileNames;

}
