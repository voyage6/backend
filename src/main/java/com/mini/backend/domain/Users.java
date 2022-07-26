package com.mini.backend.domain;

import com.mini.backend.dto.UserUpdateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity //DB 테이블 역할

public class Users extends Timestamped{

    //ID : 인덱스 번호
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동증가
    @Id
    private Long id;

    //nullable : null 허용 여부
    //unique : 중복 허용 여부 (false 일 때 중복 허용 한다는 뜻)
    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private String userName;

    @Column
    private String profileURL;

    public Users(String userId, String userPassword, String userName) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
    }

    public void update(UserUpdateRequestDto userUpdateRequestDto) {
        this.userName = userUpdateRequestDto.getUserName();
        this.profileURL = userUpdateRequestDto.getProfileUrl();
    }
}
