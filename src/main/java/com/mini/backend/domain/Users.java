package com.mini.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mini.backend.dto.UserUpdateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@Entity //DB 테이블 역할

public class Users extends Timestamped {

    //ID : 인덱스 번호
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

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
