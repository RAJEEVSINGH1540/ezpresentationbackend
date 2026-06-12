package com.example.ez.user.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth

                        // ═══════════════════════════════════════════════════
                        // 1. FULLY PUBLIC — no token needed
                        // ═══════════════════════════════════════════════════
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/uploads/**").permitAll()
                        .requestMatchers("/ws/**").permitAll()
                        .requestMatchers("/topic/**").permitAll()
                        .requestMatchers("/app/**").permitAll()

                        // ═══════════════════════════════════════════════════
                        // 2. WHATSAPP CHAT — CLIENT (Public: anyone can ask)
                        // ═══════════════════════════════════════════════════
                        .requestMatchers(HttpMethod.POST, "/api/chat/send").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/chat/history/**").permitAll()

                        // ═══════════════════════════════════════════════════
                        // 3. WHATSAPP CHAT — ADMIN (Admin only: reply, manage)
                        // ═══════════════════════════════════════════════════
                        .requestMatchers("/api/admin/conversations").hasRole("ADMIN")
                        .requestMatchers("/api/admin/conversations/**").hasRole("ADMIN")
                        .requestMatchers("/api/admin/history/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/admin/reply").hasRole("ADMIN")
                        .requestMatchers("/api/admin/reply/**").hasRole("ADMIN")
                        .requestMatchers("/api/admin/read/**").hasRole("ADMIN")
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")

                        // ═══════════════════════════════════════════════════
                        // 4. CASE STUDIES — PUBLIC (Anyone can view published)
                        // ═══════════════════════════════════════════════════
                        .requestMatchers(HttpMethod.GET,
                                "/api/projects/getAllProjectSummary",
                                "/api/projects/getCaseStudy/**"
                        ).permitAll()

                        // ═══════════════════════════════════════════════════
                        // 5. CASE STUDIES — ADMIN (Only admin can publish/edit/delete)
                        // ═══════════════════════════════════════════════════
                        .requestMatchers(HttpMethod.POST, "/api/projects/saveCase").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/projects/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/projects/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/projects/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/projects/**").hasRole("ADMIN")
                        .requestMatchers("/api/projects/admin/**").hasRole("ADMIN")

                        // ═══════════════════════════════════════════════════
                        // 6. CMS — PUBLIC GET (Live site reads)
                        // ═══════════════════════════════════════════════════
                        .requestMatchers(HttpMethod.GET,
                                "/api/cms/services",
                                "/api/cms/services/**",
                                "/api/cms/sections",
                                "/api/cms/sections/**",
                                "/api/cms/hero",
                                "/api/cms/hero/**",
                                "/api/cms/trusted-clients",
                                "/api/cms/trusted-clients/**",
                                "/api/cms/dashboard-showcase",
                                "/api/cms/dashboard-showcase/**",
                                "/api/cms/why-choose-us",
                                "/api/cms/why-choose-us/**",
                                "/api/cms/industries",
                                "/api/cms/industries/**",
                                "/api/cms/benefits",
                                "/api/cms/benefits/**",
                                "/api/cms/testimonials",
                                "/api/cms/testimonials/**",
                                "/api/cms/pricing",
                                "/api/cms/pricing/**",
                                "/api/cms/final-cta",
                                "/api/cms/final-cta/**",
                                "/api/cms/footer/public",
                                "/api/cms/navbar/**",
                                "/api/cms/*/public",
                                "/api/cms/public/**"
                        ).permitAll()

                        // ═══════════════════════════════════════════════════
                        // 7. CMS — ALL WRITES → ADMIN only
                        // ═══════════════════════════════════════════════════
                        .requestMatchers(HttpMethod.POST, "/api/cms/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/cms/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/cms/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/cms/**").hasRole("ADMIN")

                        // ═══════════════════════════════════════════════════
                        // 8. HOMEPAGE SECTIONS — PUBLIC GET
                        // ═══════════════════════════════════════════════════
                        .requestMatchers(HttpMethod.GET,
                                "/api/homepage/hero",
                                "/api/homepage/hero/**",
                                "/api/homepage/navbar",
                                "/api/homepage/navbar/**",
                                "/api/homepage/navbar/active",
                                "/api/homepage/trusted-brands",
                                "/api/homepage/trusted-brands/**",
                                "/api/homepage/industry-solutions",
                                "/api/homepage/industry-solutions/**",
                                "/api/homepage/industry-solutions/active",
                                "/api/homepage/why-choose-us",
                                "/api/homepage/why-choose-us/**",
                                "/api/homepage/dashboard-showcase",
                                "/api/homepage/dashboard-showcase/**",
                                "/api/homepage/testimonials",
                                "/api/homepage/testimonials/**",
                                "/api/homepage/pricing",
                                "/api/homepage/pricing/**",
                                "/api/homepage/final-cta",
                                "/api/homepage/final-cta/**",
                                "/api/homepage/footer",
                                "/api/homepage/footer/**",
                                "/api/homepage/**"
                        ).permitAll()

                        // ═══════════════════════════════════════════════════
                        // 9. HOMEPAGE — WRITES → ADMIN only
                        // ═══════════════════════════════════════════════════
                        .requestMatchers(HttpMethod.POST, "/api/homepage/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/homepage/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/homepage/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/homepage/**").hasRole("ADMIN")

                        // ═══════════════════════════════════════════════════
                        // 10. BOOK DEMO — PUBLIC GET/POST
                        // ═══════════════════════════════════════════════════
                        .requestMatchers(HttpMethod.GET,
                                "/api/book-demo/form",
                                "/api/book-demo/form/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/book-demo/submit").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/book-demo/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/book-demo/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/book-demo/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/book-demo/**").hasRole("ADMIN")

                        // ═══════════════════════════════════════════════════
                        // 11. CLIENT LOGOS — PUBLIC GET
                        // ═══════════════════════════════════════════════════
                        .requestMatchers(HttpMethod.GET,
                                "/api/client-logos",
                                "/api/client-logos/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/client-logos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/client-logos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/client-logos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/client-logos/**").hasRole("ADMIN")
                        .requestMatchers("/api/admin/client-logos", "/api/admin/client-logos/**").hasRole("ADMIN")

                        // ═══════════════════════════════════════════════════
                        // 12. ABOUT — PUBLIC GET /active
                        // ═══════════════════════════════════════════════════
                        .requestMatchers(HttpMethod.GET, "/api/about/active").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/about").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/about/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/about/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/about/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/about/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/about/**").hasRole("ADMIN")


                        // ═══════════════════════════════════════════════════
                        // 13. ERP WORK — PUBLIC GET
                        // ═══════════════════════════════════════════════════
                        .requestMatchers(HttpMethod.GET, "/api/erp/public/**").permitAll()
                        .requestMatchers("/api/erp/admin/**").hasRole("ADMIN")


                        // ═══════════════════════════════════════════════════
                        // 14. EVERYTHING ELSE → any valid token
                        // ═══════════════════════════════════════════════════
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