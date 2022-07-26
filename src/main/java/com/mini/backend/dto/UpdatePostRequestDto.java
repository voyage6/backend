package com.mini.backend.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class UpdatePostRequestDto {//수정 요청
    private String title;
    private String contents;
    private String category;
    private List<String> imgUrls;
}
