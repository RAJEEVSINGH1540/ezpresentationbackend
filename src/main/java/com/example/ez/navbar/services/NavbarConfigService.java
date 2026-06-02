// src/main/java/com/example/ez/homepage/navbar/NavbarConfigService.java
package com.example.ez.navbar.services;

import com.example.ez.navbar.dto.NavbarConfigDto;
import com.example.ez.navbar.entity.NavbarConfig;
import com.example.ez.navbar.repository.NavbarConfigRepository;
import com.example.ez.services.service.ImageUploadService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NavbarConfigService {

    private final NavbarConfigRepository repo;
    private final ImageUploadService      imageUploadService;
    private final ObjectMapper            objectMapper;

    // ── Get active config ─────────────────────────────────
    public NavbarConfigDto getActive() {
        NavbarConfig cfg = repo.findFirstByActiveTrue()
            .orElseGet(this::createDefault);
        return toDto(cfg);
    }

    // ── Get all ───────────────────────────────────────────
    public List<NavbarConfigDto> getAll() {
        return repo.findAll().stream().map(this::toDto).toList();
    }

    // ── Create ────────────────────────────────────────────
    public NavbarConfigDto create(NavbarConfigDto dto) {
        NavbarConfig entity = toEntity(new NavbarConfig(), dto);
        return toDto(repo.save(entity));
    }

    // ── Update ────────────────────────────────────────────
    public NavbarConfigDto update(Long id, NavbarConfigDto dto) {
        NavbarConfig entity = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("NavbarConfig not found: " + id));
        toEntity(entity, dto);
        return toDto(repo.save(entity));
    }

    // ── Upload logo image ─────────────────────────────────
    public NavbarConfigDto uploadLogo(Long id, MultipartFile file) throws Exception {
        NavbarConfig entity = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("NavbarConfig not found: " + id));
        String url = imageUploadService.upload(file);
        entity.setLogoImageUrl(url);
        return toDto(repo.save(entity));
    }

    // ── Delete ────────────────────────────────────────────
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // ── Default seeder ────────────────────────────────────
    private NavbarConfig createDefault() {
        NavbarConfig cfg = NavbarConfig.builder()
            .logoText("EZ Construction")
            .logoIconColor("#1a56e8")
            .navLinksJson("""
                [
                  {"label":"Home",      "href":"/",         "isExternal":false},
                  {"label":"Solutions", "href":"/services", "isExternal":false},
                  {"label":"About",     "href":"#about",    "isExternal":false},
                  {"label":"Contact",   "href":"#contact",  "isExternal":false}
                ]
            """)
            .ctaLabel("Explore Platform")
            .ctaHref("/services")
            .ctaColor("#1a56e8")
            .sticky(true)
            .active(true)
            .build();
        return repo.save(cfg);
    }

    // ── Mapper: entity → dto ──────────────────────────────
    private NavbarConfigDto toDto(NavbarConfig e) {
        List<NavbarConfigDto.NavLinkDto> links = List.of();
        try {
            if (e.getNavLinksJson() != null && !e.getNavLinksJson().isBlank()) {
                links = objectMapper.readValue(
                    e.getNavLinksJson(),
                    new TypeReference<List<NavbarConfigDto.NavLinkDto>>() {}
                );
            }
        } catch (Exception ex) {
            // return empty list on parse error
        }
        return NavbarConfigDto.builder()
            .id(e.getId())
            .logoImageUrl(e.getLogoImageUrl())
            .logoText(e.getLogoText())
            .logoIconColor(e.getLogoIconColor())
            .navLinks(links)
            .ctaLabel(e.getCtaLabel())
            .ctaHref(e.getCtaHref())
            .ctaColor(e.getCtaColor())
            .sticky(e.getSticky())
            .active(e.getActive())
            .build();
    }

    // ── Mapper: dto → entity ──────────────────────────────
    private NavbarConfig toEntity(NavbarConfig e, NavbarConfigDto dto) {
        if (dto.getLogoImageUrl() != null) e.setLogoImageUrl(dto.getLogoImageUrl());
        if (dto.getLogoText()     != null) e.setLogoText(dto.getLogoText());
        if (dto.getLogoIconColor()!= null) e.setLogoIconColor(dto.getLogoIconColor());
        if (dto.getCtaLabel()     != null) e.setCtaLabel(dto.getCtaLabel());
        if (dto.getCtaHref()      != null) e.setCtaHref(dto.getCtaHref());
        if (dto.getCtaColor()     != null) e.setCtaColor(dto.getCtaColor());
        if (dto.getSticky()       != null) e.setSticky(dto.getSticky());
        if (dto.getActive()       != null) e.setActive(dto.getActive());
        // serialize navLinks list → JSON string
        if (dto.getNavLinks() != null) {
            try {
                e.setNavLinksJson(objectMapper.writeValueAsString(dto.getNavLinks()));
            } catch (Exception ex) {
                e.setNavLinksJson("[]");
            }
        }
        return e;
    }
}