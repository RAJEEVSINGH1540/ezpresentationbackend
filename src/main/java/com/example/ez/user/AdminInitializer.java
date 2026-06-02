package com.example.ez.user;

import com.example.ez.user.entity.Admin;
import com.example.ez.user.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    private static final List<AdminSeed> DEFAULT_ADMINS = List.of(
            new AdminSeed("admin",      "Admin@123",      "ADMIN"),
            new AdminSeed("manager",    "Manager@456",    "MANAGER"),
            new AdminSeed("superadmin", "SuperAdmin@789", "SUPERADMIN")
    );

    @Override
    public void run(String... args) {
        DEFAULT_ADMINS.forEach(seed -> {
            if (!adminRepository.existsByUsername(seed.username())) {
                Admin admin = Admin.builder()
                        .username(seed.username())
                        .password(passwordEncoder.encode(seed.rawPassword()))
                        .role(seed.role())
                        .build();
                adminRepository.save(admin);
                log.info("✅ [AngelClap] Seeded admin: '{}'", seed.username());
            } else {
                log.info("ℹ️ [AngelClap] Admin '{}' already exists", seed.username());
            }
        });
    }

    private record AdminSeed(String username, String rawPassword, String role) {}
}