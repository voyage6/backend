package com.mini.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {
    @NotBlank
    @Length(min=3, message = "아이디는 최소 3자 이상입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "아이디는 알파벳 대소문자 또는 숫자로만 이루어져있어야 합니다.")
    private String userId;

    @NotBlank
    @Length(min=4, message = "패스워드는 최소 4자 이상입니다.")
    private String userPassword;

    @NotBlank
//    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "닉네임은 알파벳 대소문자 또는 숫자로만 이루어져있어야 합니다.")
    private String userName;

}