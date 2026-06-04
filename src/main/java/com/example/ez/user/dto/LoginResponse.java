package com.example.ez.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private String token;
    private String username;
    private String email;
    private String role;
    private long expiresInMs;
    private String project;
}