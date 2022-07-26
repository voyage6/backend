package com.mini.backend.service;

import com.mini.backend.domain.Users;
import com.mini.backend.dto.LoginRequestDto;
import com.mini.backend.dto.SignupRequestDto;
import com.mini.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Users login(LoginRequestDto loginRequestDto) {
        Users user = userRepository.findByUserId(loginRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저는 존재하지 않습니다."));
        if (passwordEncoder.matches(loginRequestDto.getUserPassword(), user.getUserPassword())) {
            System.out.println("로그인 성공");
            return user;
        } else {
            throw new NullPointerException("사용자가 존재하지 않습니다");
        }
    }

    public Users registerUser(SignupRequestDto requestDto) {

        Optional<Users> found = userRepository.findByUserId(requestDto.getUserId());
        if (found.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        String userPassword = passwordEncoder.encode(requestDto.getUserPassword());//비밀번호 암호화
        requestDto.setUserPassword(userPassword);//암호화된 비밀번호 set

        Users user = new Users(requestDto);
        userRepository.save(user);
        return user;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

}
