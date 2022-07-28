package com.mini.backend.service;

import com.mini.backend.domain.Users;
import com.mini.backend.dto.*;
import com.mini.backend.repository.UserRepository;
import com.mini.backend.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

//    public ResponseEntity<?> idCheck(IdCheckDto checkDto) {
//        if(userRepository.findByUserId(checkDto.getUserId()).isPresent())
//            return new ResponseEntity<>("이미 존재하는 아이디 입니다.", HttpStatus.BAD_REQUEST);
//        else return new ResponseEntity<>("사용가능한 아이디 입니다.", HttpStatus.OK);
//    }

    public ResponseEntity<?> idCheck(IdCheckDto idCheckDto) {
        if (userRepository.findByUserId(idCheckDto.getId()).isPresent())
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

    public UserResponseDto getUser(UserDetailsImpl userDetails) {
        Users user = userRepository.findByUserId(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("ㅇㄴㄹㄴㅇㄹㄴ"));
        return new UserResponseDto(userDetails.getUser());

    }

    public ResponseEntity<?> updateUser(Long userId, UserUpdateRequestDto userUpdateRequestDto, UserDetailsImpl userDetails) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        if (!userId.equals(userDetails.getUser().getId()))
            return new ResponseEntity<>(HttpStatus.valueOf(403));
        else {
            user.update(userUpdateRequestDto);
            userRepository.save(user);
            return new ResponseEntity<>(HttpStatus.valueOf(204));
        }
    }

}
