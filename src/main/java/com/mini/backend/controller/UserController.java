package com.mini.backend.controller;


//Dto를 가져오는 컨트롤러

import com.mini.backend.dto.IdCheckDto;
import com.mini.backend.dto.SignupRequestDto;
import com.mini.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //회원 가입 요청
    @PostMapping("/api/users/signup")
    public ResponseEntity registerUser(SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return new ResponseEntity<>("회원가입에 성공하였습니다. ", HttpStatus.valueOf(200));
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
