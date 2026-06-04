package com.example.ez.user.service;

import com.example.ez.user.dto.LoginRequest;
import com.example.ez.user.dto.LoginResponse;
import com.example.ez.user.entity.Admin;
import com.example.ez.user.exception.UnauthorizedException;
import com.example.ez.user.jwt.JwtUtil;
import com.example.ez.user.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Value("${ez.jwt.expiration-ms}")
    private long expirationMs;

    public LoginResponse login(LoginRequest request) {

        Admin admin = adminRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new UnauthorizedException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            log.warn("⚠️ [EZ] Failed login for '{}'", request.getUsername());
            throw new UnauthorizedException("Invalid username or password");
        }

        String token = jwtUtil.generateToken(admin.getUsername(), admin.getRole());
        log.info("✅ [EZ] Admin '{}' logged in", admin.getUsername());

        return LoginResponse.builder()
                .token(token)
                .username(admin.getUsername())
                .role(admin.getRole())
                .expiresInMs(expirationMs)
                .project("EZ")
                .build();
    }
}