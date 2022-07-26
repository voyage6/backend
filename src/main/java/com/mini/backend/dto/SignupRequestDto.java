package com.mini.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//회원가입 요청에 대한 Dto
public class SignupRequestDto {
    private String userId;
    private String userPassword;
    private String userName;
    private String profileURL;
}
