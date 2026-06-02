package com.example.ez.footter.service;

import com.example.ez.footter.dto.*;
import com.example.ez.footter.entity.FooterContent;
import com.example.ez.footter.repository.FooterRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FooterService {

    private final FooterRepository repo;
    private final ObjectMapper      mapper;

    private static final Long FOOTER_ID = 1L;

    // ── GET ──────────────────────────────────────────────────────
    @Transactional
    public FooterDto get() {
        return toDto(
                repo.findById(FOOTER_ID).orElseGet(this::insertDefault)
        );
    }

    // ── SAVE (uses JPQL UPDATE — never merge a detached entity) ──
    @Transactional
    public FooterDto save(FooterDto dto) {
        try {
            String socialJson  = mapper.writeValueAsString(nullSafe(dto.getSocialLinks()));
            String quickJson   = mapper.writeValueAsString(nullSafe(dto.getQuickLinks()));
            String serviceJson = mapper.writeValueAsString(nullSafe(dto.getServiceLinks()));
            String infoJson    = mapper.writeValueAsString(nullSafe(dto.getInfoLinks()));

            // Try UPDATE first
            int updated = repo.updateById(
                    FOOTER_ID,
                    dto.getLogoUrl(),
                    dto.getDescription(),
                    dto.getCopyrightText(),
                    dto.getQuickLinksHeading(),
                    dto.getServicesHeading(),
                    dto.getInformationHeading(),
                    socialJson,
                    quickJson,
                    serviceJson,
                    infoJson
            );

            // If no row exists yet, INSERT
            if (updated == 0) {
                FooterContent entity = buildEntity(dto, socialJson, quickJson, serviceJson, infoJson);
                repo.saveAndFlush(entity);
            }

            // Re-fetch fresh from DB and return
            return toDto(repo.findById(FOOTER_ID).orElseThrow());

        } catch (Exception e) {
            log.error("FooterService.save failed", e);
            throw new RuntimeException("Failed to save footer: " + e.getMessage(), e);
        }
    }

    // ── HELPERS ───────────────────────────────────────────────────
    private FooterContent insertDefault() {
        FooterDto defaults = buildDefaults();
        try {
            FooterContent entity = buildEntity(
                    defaults,
                    mapper.writeValueAsString(defaults.getSocialLinks()),
                    mapper.writeValueAsString(defaults.getQuickLinks()),
                    mapper.writeValueAsString(defaults.getServiceLinks()),
                    mapper.writeValueAsString(defaults.getInfoLinks())
            );
            return repo.saveAndFlush(entity);
        } catch (Exception e) {
            throw new RuntimeException("Failed to insert default footer", e);
        }
    }

    private FooterContent buildEntity(
            FooterDto dto,
            String socialJson, String quickJson,
            String serviceJson, String infoJson
    ) {
        return FooterContent.builder()
                .id(FOOTER_ID)
                .logoUrl(dto.getLogoUrl())
                .description(dto.getDescription())
                .copyrightText(dto.getCopyrightText())
                .quickLinksHeading(dto.getQuickLinksHeading())
                .servicesHeading(dto.getServicesHeading())
                .informationHeading(dto.getInformationHeading())
                .socialLinksJson(socialJson)
                .quickLinksJson(quickJson)
                .serviceLinksJson(serviceJson)
                .infoLinksJson(infoJson)
                .build();
    }

    private FooterDto toDto(FooterContent e) {
        return FooterDto.builder()
                .id(e.getId())
                .logoUrl(e.getLogoUrl())
                .description(e.getDescription())
                .copyrightText(e.getCopyrightText())
                .quickLinksHeading(e.getQuickLinksHeading())
                .servicesHeading(e.getServicesHeading())
                .informationHeading(e.getInformationHeading())
                .socialLinks(parseList(e.getSocialLinksJson(),
                        new TypeReference<List<SocialLink>>() {}))
                .quickLinks(parseList(e.getQuickLinksJson(),
                        new TypeReference<List<LinkItem>>() {}))
                .serviceLinks(parseList(e.getServiceLinksJson(),
                        new TypeReference<List<LinkItem>>() {}))
                .infoLinks(parseList(e.getInfoLinksJson(),
                        new TypeReference<List<LinkItem>>() {}))
                .build();
    }

    private <T> List<T> parseList(String json, TypeReference<List<T>> ref) {
        if (json == null || json.isBlank()) return List.of();
        try { return mapper.readValue(json, ref); }
        catch (Exception e) { return List.of(); }
    }

    private <T> List<T> nullSafe(List<T> list) {
        return list != null ? list : List.of();
    }

    private FooterDto buildDefaults() {
        return FooterDto.builder()
                .logoUrl("")
                .description("Each demo built with Teba will look different. You can customize almost anything in the appearance of your website with only a few clicks.")
                .copyrightText("Copyright © 2024 EZ Construction. All rights reserved.")
                .quickLinksHeading("Quick Links")
                .servicesHeading("Services")
                .informationHeading("Information")
                .socialLinks(List.of(
                        new SocialLink("facebook",  "#"),
                        new SocialLink("linkedin",  "#"),
                        new SocialLink("twitter",   "#"),
                        new SocialLink("instagram", "#")
                ))
                .quickLinks(List.of(
                        new LinkItem("About Us",   "/about"),
                        new LinkItem("Our Team",   "#"),
                        new LinkItem("Pricing",    "#"),
                        new LinkItem("Blogs",      "#"),
                        new LinkItem("Contact Us", "#")
                ))
                .serviceLinks(List.of(
                        new LinkItem("UI/UX Design",      "#"),
                        new LinkItem("App Development",   "#"),
                        new LinkItem("Digital Marketing", "#"),
                        new LinkItem("Web Development",   "#"),
                        new LinkItem("Cyber Security",    "#")
                ))
                .infoLinks(List.of(
                        new LinkItem("Working Process",    "#"),
                        new LinkItem("Privacy Policy",     "#"),
                        new LinkItem("Terms & Conditions", "#"),
                        new LinkItem("Faqs",               "#")
                ))
                .build();
    }
}