package com.mini.backend.controller;


//Dto를 가져오는 컨트롤러

import com.mini.backend.dto.IdCheckDto;
import com.mini.backend.dto.SignupRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    //회원 가입 요청
    @PostMapping("/api/users/signup")
    public String registerUser(SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "회원가입에 성공하였습니다."; // or 회원가입에 실패했습니다.
    }

    //아이디 중복확인
    @PostMapping("api/users/idCheck")
    public String idCheck(IdCheckDto checkDto) {

        return "";
    }

    //로그인 처리
    @PostMapping("api/users/login")
    public String login() {
        return "로그인 성공";
    }

    //로그아웃
    @PostMapping("api/users/logout")
    public String logout() {
        return "redirect:/users/login";
    }

}
