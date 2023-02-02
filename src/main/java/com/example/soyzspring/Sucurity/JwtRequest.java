package com.example.soyzspring.Sucurity;

import lombok.Data;

@Data
public class JwtRequest {

    private String username;
    private String password;
}
