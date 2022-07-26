package com.mini.backend.controller;


//Dto를 가져오는 컨트롤러

import com.mini.backend.dto.IdCheckDto;
import com.mini.backend.dto.SignupRequestDto;
import com.mini.backend.dto.UpdatePostRequestDto;
import com.mini.backend.dto.UserUpdateRequestDto;
import com.mini.backend.security.UserDetailsImpl;
import com.mini.backend.service.UserService;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //회원 가입 요청
    @PostMapping("/api/users/signup")
    public ResponseEntity registerUser(@RequestBody SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return new ResponseEntity<>("회원가입에 성공하였습니다. ", HttpStatus.valueOf(201));
    }

    //아이디 중복확인
    @PostMapping("api/users/idCheck")
    public ResponseEntity idCheck(@RequestBody IdCheckDto checkDto) {
        userService.idCheck(checkDto);
        return new ResponseEntity<>("이미 존재하는 아이디입니다.", HttpStatus.valueOf(200));
    }

    //로그인 처리
//    @PostMapping("api/users/login")
//    public String login() {
//        return "로그인 성공"; // ?
//    }

    //로그아웃
//    @PostMapping("api/users/logout")
//    public String logout() {
//        return "redirect:/users/login";
//    }

    @GetMapping("/api/users")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new ResponseEntity<>(userService.getUser(userDetails), HttpStatus.valueOf(200));
    }

    @PatchMapping("/api/users/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UserUpdateRequestDto userUpdateRequestDto,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.updateUser(userId, userUpdateRequestDto, userDetails);
    }

}
