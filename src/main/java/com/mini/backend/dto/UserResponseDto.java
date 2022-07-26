package com.mini.backend.dto;

import com.mini.backend.domain.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private Long userId;
    private String userName;
    private String imgUrl;
    private String createdAt;
    private String updatedAt;

    public UserResponseDto(Users user) {
        this.userId = user.getId();
        this.userName = user.getUserName();
        this.imgUrl = user.getProfileURL();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}
