package com.example.soyzspring.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterUserDto {
    private String username;
    private String password;
    private String confirmPassword;
}
