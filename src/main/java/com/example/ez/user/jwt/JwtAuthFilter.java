// com/example/ez/user/jwt/JwtAuthFilter.java
package com.example.ez.user.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    // ✅ Paths that NEVER need a token (any HTTP method)
    private static final List<String> PUBLIC_PATHS = List.of(
            "/api/auth/**",
            "/api/projects/**",        // ✅ Case study — public GET
            "/api/chat/**",            // ✅ Client chat — no token
            "/api/cms/footer/public",
            "/api/cms/navbar/**",
            "/api/cms/hero/**",
            "/api/cms/*/public",
            "/api/cms/public/**",
            "/api/homepage/**",
            "/api/navbar/**",
            "/api/about/**",
            "/api/services/**",
            "/api/pricing/**",
            "/api/book-demo/**",
            "/api/testimonials/**",
            "/api/contact/submit",
            "/api/public/**",
            "/api/blogs/*/view",
            "/ws/**",
            "/ws",
            "/topic/**",
            "/app/**",
            "/queue/**",
            "/uploads/**"
    );

    // ✅ Paths that ALWAYS need a token (all HTTP methods including GET)
    private static final List<String> PROTECTED_PATHS = List.of(
            "/api/admin/**"            // ✅ Admin — always needs token
    );

    @Override
    protected void doFilterInternal(
            HttpServletRequest  request,
            HttpServletResponse response,
            FilterChain         filterChain
    ) throws ServletException, IOException {

        String method = request.getMethod();
        String path   = request.getRequestURI();

        // ✅ 1. OPTIONS pre-flight → always pass
        if (HttpMethod.OPTIONS.name().equals(method)) {
            filterChain.doFilter(request, response);
            return;
        }

        // ✅ 2. Public paths → skip JWT entirely, no token needed
        if (isPublicPath(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        // ✅ 3. Protected paths (e.g. /api/admin/**) → ALWAYS validate token
        //       regardless of HTTP method (GET, POST, PUT, DELETE)
        if (isProtectedPath(path)) {
            String token = extractToken(request);

            if (token == null) {
                log.warn("⚠️ [Auth] No token → {} {}", method, path);
                sendError(response, HttpServletResponse.SC_UNAUTHORIZED,
                        "Missing Authorization header");
                return;
            }

            if (!jwtUtil.validateToken(token)) {
                log.warn("⚠️ [Auth] Invalid token → {} {}", method, path);
                sendError(response, HttpServletResponse.SC_UNAUTHORIZED,
                        "Invalid or expired token");
                return;
            }

            String username = jwtUtil.extractUsername(token);
            String role     = jwtUtil.extractRole(token);

            var auth = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    List.of(new SimpleGrantedAuthority("ROLE_" + role))
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
            log.info("✅ [Auth] '{}' [{}] → {} {}", username, role, method, path);

            filterChain.doFilter(request, response);
            return;
        }

        // ✅ 4. Everything else:
        //    - GET  → pass freely (no token needed)
        //    - POST/PUT/PATCH/DELETE → validate token
        if (HttpMethod.GET.name().equals(method)) {
            filterChain.doFilter(request, response);
            return;
        }

        // POST / PUT / PATCH / DELETE on non-public, non-protected paths
        String token = extractToken(request);

        if (token == null) {
            log.warn("⚠️ [Auth] No token → {} {}", method, path);
            sendError(response, HttpServletResponse.SC_UNAUTHORIZED,
                    "Missing Authorization header");
            return;
        }

        if (!jwtUtil.validateToken(token)) {
            log.warn("⚠️ [Auth] Invalid token → {} {}", method, path);
            sendError(response, HttpServletResponse.SC_UNAUTHORIZED,
                    "Invalid or expired token");
            return;
        }

        String username = jwtUtil.extractUsername(token);
        String role     = jwtUtil.extractRole(token);

        var auth = new UsernamePasswordAuthenticationToken(
                username,
                null,
                List.of(new SimpleGrantedAuthority("ROLE_" + role))
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        log.info("✅ [Auth] '{}' [{}] → {} {}", username, role, method, path);

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    private boolean isPublicPath(String path) {
        return PUBLIC_PATHS.stream()
                .anyMatch(pattern -> pathMatcher.match(pattern, path));
    }

    private boolean isProtectedPath(String path) {
        return PROTECTED_PATHS.stream()
                .anyMatch(pattern -> pathMatcher.match(pattern, path));
    }

    private void sendError(HttpServletResponse response, int status, String message)
            throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.getWriter().write("""
                {
                  "status": %d,
                  "error": "%s",
                  "message": "%s"
                }
                """.formatted(
                status,
                status == 401 ? "Unauthorized" : "Forbidden",
                message));
    }
}