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
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    // =========================================================================
    // FULLY PUBLIC — No token needed for ANY HTTP method
    // =========================================================================
    private static final List<String> FULLY_PUBLIC = List.of(
            "/api/auth/**",
            "/uploads/**",
            "/ws/**",
            "/topic/**",
            "/app/**"
    );

    // =========================================================================
    // PUBLIC GET ONLY — These paths allow GET without token
    // =========================================================================
    private static final List<String> PUBLIC_GET_PATHS;

    static {
        PUBLIC_GET_PATHS = new ArrayList<>(List.of(

                // ── WHATSAPP CHAT ─────────────────────────────────────────
                "/api/chat/history",
                "/api/chat/history/**",

                // ── ABOUT ─────────────────────────────────────────────────
                "/api/about/active",

                // ── BOOK DEMO ─────────────────────────────────────────────
                "/api/book-demo/form",
                "/api/book-demo/form/**",

                // ── CLIENT LOGOS ──────────────────────────────────────────
                "/api/client-logos",
                "/api/client-logos/**",

                // ── ERP WORK  ──────────────────────────────────────────
                "/api/erp/public/**",


                // ── HOMEPAGE SECTIONS ─────────────────────────────────────
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
                "/api/homepage/**",

                // ── CMS SERVICE DETAIL ────────────────────────────────────
                "/api/cms/services",
                "/api/cms/services/**",

                // ── CMS HOMEPAGE SECTIONS ─────────────────────────────────
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
                "/api/cms/public/**",

                // ── CASE STUDIES / PROJECTS ─────────────────────────────
                "/api/projects/getAllProjectSummary",
                "/api/projects/getCaseStudy/**"
        ));
    }

    // =========================================================================
    // PUBLIC POST — Anyone can submit (no token needed)
    // =========================================================================
    private static final List<String> PUBLIC_POST_PATHS = List.of(
            "/api/chat/send",
            "/api/book-demo/submit",
            "/api/auth/**"
    );

    // =========================================================================
    // ADMIN-ONLY WRITE PATHS
    // =========================================================================
    private static final List<String> ADMIN_WRITE_BASE_PATHS = List.of(
            "/api/about",
            "/api/about/**",
            "/api/cms",
            "/api/cms/**",
            "/api/homepage",
            "/api/homepage/**",
            "/api/client-logos",
            "/api/client-logos/**",
            "/api/book-demo",
            "/api/book-demo/**",
            "/api/projects",
            "/api/projects/**",
            "/api/admin",
            "/api/admin/**"
    );

    private static final List<String> WRITE_METHODS = List.of(
            HttpMethod.POST.name(),
            HttpMethod.PUT.name(),
            HttpMethod.PATCH.name(),
            HttpMethod.DELETE.name()
    );

    // =========================================================================
    // MAIN FILTER LOGIC
    // =========================================================================
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String method = request.getMethod();
        String path = request.getRequestURI();

        log.debug("🔍 [Filter] {} {}", method, path);

        // ═══════════════════════════════════════════════════════════════════
        // 0. AUTH ENDPOINTS — Always bypass, never check token
        // ═══════════════════════════════════════════════════════════════════
        if (path.startsWith("/api/auth")) {
            log.debug("✅ [Auth Bypass] {} {}", method, path);
            filterChain.doFilter(request, response);
            return;
        }

        // ── 1. OPTIONS preflight → always pass (CORS) ────────────────────────
        if (HttpMethod.OPTIONS.name().equals(method)) {
            filterChain.doFilter(request, response);
            return;
        }

        // ── 2. Fully public → no token at all ──────────────────────────────
        if (isMatch(FULLY_PUBLIC, path)) {
            log.debug("✅ [Public] {} {}", method, path);
            filterChain.doFilter(request, response);
            return;
        }

        // ── 3. Public POST paths → no token needed ───────────────────────
        if (HttpMethod.POST.name().equals(method) && isMatch(PUBLIC_POST_PATHS, path)) {
            log.debug("✅ [Public POST] {}", path);
            filterChain.doFilter(request, response);
            return;
        }

        // ── 4. Write methods on protected paths → ADMIN required ─────────
        if (isWriteMethod(method) && isAdminWritePath(path)) {
            log.debug("🔒 [Admin Write] {} {}", method, path);
            if (!validateAndRequireRole(
                    request, response, method, path, "ROLE_ADMIN")) {
                return;
            }
            filterChain.doFilter(request, response);
            return;
        }

        // ── 5. Public GET paths → no token needed ────────────────────────
        if (HttpMethod.GET.name().equals(method) && isPublicGet(path)) {
            log.debug("✅ [Public GET] {}", path);
            filterChain.doFilter(request, response);
            return;
        }

        // ── 6. Everything else → any valid token ─────────────────────────
        if (!validateAndSetAuth(request, response, method, path)) {
            return;
        }
        filterChain.doFilter(request, response);
    }

    // =========================================================================
    // TOKEN VALIDATION — Requires ROLE_ADMIN
    // =========================================================================
    private boolean validateAndRequireRole(
            HttpServletRequest request,
            HttpServletResponse response,
            String method,
            String path,
            String requiredRole
    ) throws IOException {

        String token = extractToken(request);

        if (token == null) {
            log.warn("⚠️ [Auth] No token → {} {}", method, path);
            sendError(response,
                    HttpServletResponse.SC_UNAUTHORIZED,
                    "Missing Authorization header — admin login required");
            return false;
        }

        if (!jwtUtil.validateToken(token)) {
            log.warn("⚠️ [Auth] Invalid/expired token → {} {}", method, path);
            sendError(response,
                    HttpServletResponse.SC_UNAUTHORIZED,
                    "Invalid or expired token — please log in again");
            return false;
        }

        String username = jwtUtil.extractUsername(token);
        String role = jwtUtil.extractRole(token);
        String authority = role.startsWith("ROLE_") ? role : "ROLE_" + role;

        if (!authority.equals(requiredRole)) {
            log.warn("⚠️ [Auth] Forbidden — '{}' has [{}] needs [{}] → {} {}",
                    username, authority, requiredRole, method, path);
            sendError(response,
                    HttpServletResponse.SC_FORBIDDEN,
                    "Access denied — admin role required");
            return false;
        }

        setAuthentication(username, authority);
        log.info("✅ [Auth] '{}' [{}] → {} {}", username, authority, method, path);
        return true;
    }

    // =========================================================================
    // TOKEN VALIDATION — Any valid token (no role check)
    // =========================================================================
    private boolean validateAndSetAuth(
            HttpServletRequest request,
            HttpServletResponse response,
            String method,
            String path
    ) throws IOException {

        String token = extractToken(request);

        if (token == null) {
            log.warn("⚠️ [Auth] No token → {} {}", method, path);
            sendError(response,
                    HttpServletResponse.SC_UNAUTHORIZED,
                    "Missing Authorization header");
            return false;
        }

        if (!jwtUtil.validateToken(token)) {
            log.warn("⚠️ [Auth] Invalid/expired token → {} {}", method, path);
            sendError(response,
                    HttpServletResponse.SC_UNAUTHORIZED,
                    "Invalid or expired token");
            return false;
        }

        String username = jwtUtil.extractUsername(token);
        String role = jwtUtil.extractRole(token);
        String authority = role.startsWith("ROLE_") ? role : "ROLE_" + role;

        setAuthentication(username, authority);
        log.info("✅ [Auth] '{}' [{}] → {} {}", username, authority, method, path);
        return true;
    }

    // =========================================================================
    // HELPERS
    // =========================================================================

    private void setAuthentication(String username, String authority) {
        var auth = new UsernamePasswordAuthenticationToken(
                username,
                null,
                List.of(new SimpleGrantedAuthority(authority))
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    private boolean isMatch(List<String> patterns, String path) {
        return patterns.stream()
                .anyMatch(p -> pathMatcher.match(p, path));
    }

    private boolean isPublicGet(String path) {
        return isMatch(PUBLIC_GET_PATHS, path);
    }

    private boolean isAdminWritePath(String path) {
        return isMatch(ADMIN_WRITE_BASE_PATHS, path);
    }

    private boolean isWriteMethod(String method) {
        return WRITE_METHODS.contains(method);
    }

    private void sendError(
            HttpServletResponse response,
            int status,
            String message
    ) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("""
                {
                  "status": %d,
                  "error": "%s",
                  "message": "%s"
                }
                """.formatted(
                status,
                status == 401 ? "Unauthorized" : "Forbidden",
                message
        ));
    }
}