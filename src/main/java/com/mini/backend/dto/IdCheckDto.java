package com.mini.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

//Id 중복확인 요청에 대한 Dto
public class IdCheckDto {
    private String id;
}
