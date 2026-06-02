package com.example.ez.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${cms.upload.dir:uploads}")
    private String baseDir;

    private static final List<String> ALLOWED_ORIGINS = List.of(
            "http://localhost:5173",
            "http://localhost:3000",
            "https://angelclap.in",
            "https://69fdd1ac3d693878fce07c2e--therajeevsingh.netlify.app"
    );

    private static final List<String> ALLOWED_METHODS = List.of(
            "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"
    );

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistration() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // ========== API config: NO credentials ==========
        CorsConfiguration apiConfig = new CorsConfiguration();
        ALLOWED_ORIGINS.forEach(apiConfig::addAllowedOrigin);
        apiConfig.addAllowedHeader("*");
        ALLOWED_METHODS.forEach(apiConfig::addAllowedMethod);
        apiConfig.addExposedHeader("Authorization");
        apiConfig.addExposedHeader("Content-Disposition");
        apiConfig.setAllowCredentials(false);
        apiConfig.setMaxAge(3600L);
        source.registerCorsConfiguration("/api/**", apiConfig);

        // ========== WebSocket config: WITH credentials ==========
        // SockJS sends withCredentials:true by default
        CorsConfiguration wsConfig = new CorsConfiguration();
        ALLOWED_ORIGINS.forEach(wsConfig::addAllowedOrigin);
        wsConfig.addAllowedHeader("*");
        wsConfig.addAllowedMethod("GET");
        wsConfig.addAllowedMethod("POST");
        wsConfig.addAllowedMethod("OPTIONS");
        wsConfig.setAllowCredentials(true);
        wsConfig.setMaxAge(3600L);
        source.registerCorsConfiguration("/ws/**", wsConfig);

        // ========== Uploads config ==========
        CorsConfiguration uploadsConfig = new CorsConfiguration();
        uploadsConfig.addAllowedOriginPattern("*");
        uploadsConfig.addAllowedHeader("*");
        uploadsConfig.addAllowedMethod("GET");
        uploadsConfig.addAllowedMethod("OPTIONS");
        uploadsConfig.setAllowCredentials(false);
        uploadsConfig.setMaxAge(86400L);
        source.registerCorsConfiguration("/uploads/**", uploadsConfig);

        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String location = "file:"
                + System.getProperty("user.dir")
                + "/" + baseDir + "/";
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(location)
                .setCachePeriod(3600);
    }
}