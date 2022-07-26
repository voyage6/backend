package com.mini.backend.controller;

import com.mini.backend.domain.Users;
import com.mini.backend.dto.LoginRequestDto;
import com.mini.backend.dto.SignupRequestDto;
import com.mini.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    //로그인
    @PostMapping("/users/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
        Users user = userService.login(loginRequestDto);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    // 회원 가입 요청 처리
    @PostMapping("/users/signup")
    public Users registerUser(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errorList = bindingResult.getAllErrors();
            for (ObjectError error : errorList)
                System.out.println(error.getDefaultMessage());
        }
        Users user = userService.registerUser(requestDto);
        return user;
    }
}
