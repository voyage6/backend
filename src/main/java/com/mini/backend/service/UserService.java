package com.mini.backend.service;

import com.mini.backend.domain.Users;
import com.mini.backend.dto.IdCheckDto;
import com.mini.backend.dto.SignupRequestDto;
import com.mini.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public ResponseEntity<?> idCheck(IdCheckDto checkDto) {
        if(userRepository.findByUserId(checkDto.getUserId()).isPresent())
            return new ResponseEntity<>("이미 존재하는 아이디 입니다.", HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>("사용가능한 아이디 입니다.", HttpStatus.OK);

    }

    public void registerUser(SignupRequestDto requestDto) {
        String userId = requestDto.getUserId();
        String userPassword = passwordEncoder.encode(requestDto.getUserPassword()); // 패스워드 암호화
        String userName = requestDto.getUserName();

        Users user = new Users(userId, userPassword, userName);
        userRepository.save(user);
    }

}
