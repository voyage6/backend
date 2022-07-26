package com.mini.backend.domain;

import com.mini.backend.dto.SignupRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Users{
    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false, unique = true)//중복 허용 X
    private String userId;


    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private String userName;

    @Column
    private String profileURL;

    public Users(SignupRequestDto requestDto) {
        this.userId = requestDto.getUserId();
        this.userPassword= requestDto.getUserPassword();
        this.userName= requestDto.getUserName();
    }
}