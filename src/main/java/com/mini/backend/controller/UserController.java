package com.mini.backend.controller;


//Dto를 가져오는 컨트롤러

import com.mini.backend.domain.Users;
import com.mini.backend.dto.*;
import com.mini.backend.security.UserDetailsImpl;
import com.mini.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //회원 가입 요청
    @PostMapping("/api/users/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return new ResponseEntity<>("회원가입에 성공하였습니다. ", HttpStatus.valueOf(201));
    }

    //아이디 중복확인
    @PostMapping("api/users/idCheck")
    public ResponseEntity<?> idCheck(@RequestBody IdCheckDto checkDto) {
        return userService.idCheck(checkDto);
    }

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
