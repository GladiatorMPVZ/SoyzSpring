package com.example.soyzspring.Controllers;

import com.example.soyzspring.Dto.RegisterUserDto;
import com.example.soyzspring.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @PostMapping("/register")
    public void registrateNewUser(@RequestBody RegisterUserDto registerUserDto) {
        String bcryptChachedPassword = passwordEncoder.encode(registerUserDto.getPassword());
    }
}
