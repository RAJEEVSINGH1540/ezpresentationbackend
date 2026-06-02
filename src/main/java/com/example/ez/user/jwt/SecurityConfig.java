// com/example/ez/user/jwt/SecurityConfig.java
package com.example.ez.user.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth

                        // ══════════════════════════════════════════════
                        // PUBLIC — No token needed
                        // ══════════════════════════════════════════════

                        .requestMatchers("/api/auth/**").permitAll()

                        // ✅ Case study — public
                        .requestMatchers("/api/projects/**").permitAll()

                        // ✅ Client chat — no token
                        .requestMatchers("/api/chat/**").permitAll()

                        // CMS
                        .requestMatchers("/api/cms/footer/public").permitAll()
                        .requestMatchers("/api/cms/navbar/**").permitAll()
                        .requestMatchers("/api/cms/hero/**").permitAll()
                        .requestMatchers("/api/cms/*/public").permitAll()
                        .requestMatchers("/api/cms/public/**").permitAll()

                        // Site sections
                        .requestMatchers("/api/homepage/**").permitAll()
                        .requestMatchers("/api/navbar/**").permitAll()
                        .requestMatchers("/api/about/**").permitAll()
                        .requestMatchers("/api/services/**").permitAll()
                        .requestMatchers("/api/pricing/**").permitAll()
                        .requestMatchers("/api/book-demo/**").permitAll()
                        .requestMatchers("/api/testimonials/**").permitAll()
                        .requestMatchers("/api/contact/submit").permitAll()
                        .requestMatchers("/api/public/**").permitAll()
                        .requestMatchers("/api/blogs/*/view").permitAll()

                        // WebSocket
                        .requestMatchers(
                                "/ws", "/ws/**",
                                "/topic/**", "/app/**", "/queue/**"
                        ).permitAll()

                        // Static
                        .requestMatchers("/uploads/**").permitAll()

                        // ══════════════════════════════════════════════
                        // PROTECTED — Token always required (GET included)
                        // ══════════════════════════════════════════════

                        // ✅ Admin routes — JwtAuthFilter enforces token
                        //    for ALL methods including GET
                        .requestMatchers("/api/admin/**").authenticated()

                        // Everything else
                        .anyRequest().authenticated()
                )

                .addFilterBefore(
                        jwtAuthFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}