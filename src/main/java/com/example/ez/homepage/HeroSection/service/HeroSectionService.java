package com.example.ez.homepage.HeroSection.service;

import com.example.ez.homepage.HeroSection.dto.HeroSectionDto;
import com.example.ez.homepage.HeroSection.entity.HomeHeroSection;
import com.example.ez.homepage.HeroSection.repository.HomeHeroSectionRepository;
import com.example.ez.services.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HeroSectionService {

    private final HomeHeroSectionRepository repo;
    private final ImageUploadService imageUploadService;

    // ── Mapping ─────────────────────────────────────────────────
    private HeroSectionDto toDto(HomeHeroSection e) {
        return HeroSectionDto.builder()
                .id(e.getId())
                .eyebrowLabel(e.getEyebrowLabel())
                .headlineLine1(e.getHeadlineLine1())
                .headlineLine2(e.getHeadlineLine2())
                .headlineHighlight(e.getHeadlineHighlight())
                .subheadline(e.getSubheadline())
                .stat1Value(e.getStat1Value()).stat1Label(e.getStat1Label())
                .stat2Value(e.getStat2Value()).stat2Label(e.getStat2Label())
                .stat3Value(e.getStat3Value()).stat3Label(e.getStat3Label())
                .stat4Value(e.getStat4Value()).stat4Label(e.getStat4Label())
                .bgImageUrl(e.getBgImageUrl())
                .dashboardImageUrl(e.getDashboardImageUrl())
                .overviewCardImageUrl(e.getOverviewCardImageUrl())
                .progressCardImageUrl(e.getProgressCardImageUrl())
                .build();
    }

    private HomeHeroSection toEntity(HeroSectionDto dto) {
        return HomeHeroSection.builder()
                .eyebrowLabel(dto.getEyebrowLabel())
                .headlineLine1(dto.getHeadlineLine1())
                .headlineLine2(dto.getHeadlineLine2())
                .headlineHighlight(dto.getHeadlineHighlight())
                .subheadline(dto.getSubheadline())
                .stat1Value(dto.getStat1Value()).stat1Label(dto.getStat1Label())
                .stat2Value(dto.getStat2Value()).stat2Label(dto.getStat2Label())
                .stat3Value(dto.getStat3Value()).stat3Label(dto.getStat3Label())
                .stat4Value(dto.getStat4Value()).stat4Label(dto.getStat4Label())
                .bgImageUrl(dto.getBgImageUrl())
                .dashboardImageUrl(dto.getDashboardImageUrl())
                .overviewCardImageUrl(dto.getOverviewCardImageUrl())
                .progressCardImageUrl(dto.getProgressCardImageUrl())
                .build();
    }

    // ── CRUD ─────────────────────────────────────────────────────
    public List<HeroSectionDto> findAll() {
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public HeroSectionDto findById(Long id) {
        return repo.findById(id).map(this::toDto)
                .orElseThrow(() -> new RuntimeException("HeroSection not found: " + id));
    }

    public HeroSectionDto create(HeroSectionDto dto) {
        return toDto(repo.save(toEntity(dto)));
    }

    public HeroSectionDto update(Long id, HeroSectionDto dto) {
        HomeHeroSection existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("HeroSection not found: " + id));
        existing.setEyebrowLabel(dto.getEyebrowLabel());
        existing.setHeadlineLine1(dto.getHeadlineLine1());
        existing.setHeadlineLine2(dto.getHeadlineLine2());
        existing.setHeadlineHighlight(dto.getHeadlineHighlight());
        existing.setSubheadline(dto.getSubheadline());
        existing.setStat1Value(dto.getStat1Value()); existing.setStat1Label(dto.getStat1Label());
        existing.setStat2Value(dto.getStat2Value()); existing.setStat2Label(dto.getStat2Label());
        existing.setStat3Value(dto.getStat3Value()); existing.setStat3Label(dto.getStat3Label());
        existing.setStat4Value(dto.getStat4Value()); existing.setStat4Label(dto.getStat4Label());
        if (dto.getBgImageUrl() != null) existing.setBgImageUrl(dto.getBgImageUrl());
        if (dto.getDashboardImageUrl() != null) existing.setDashboardImageUrl(dto.getDashboardImageUrl());
        if (dto.getOverviewCardImageUrl() != null) existing.setOverviewCardImageUrl(dto.getOverviewCardImageUrl());
        if (dto.getProgressCardImageUrl() != null) existing.setProgressCardImageUrl(dto.getProgressCardImageUrl());
        return toDto(repo.save(existing));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public HeroSectionDto uploadImage(Long id, String field, MultipartFile file) throws IOException {
        HomeHeroSection existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("HeroSection not found: " + id));
        String url = imageUploadService.upload(file);
        switch (field) {
            case "bg"          -> existing.setBgImageUrl(url);
            case "dashboard"   -> existing.setDashboardImageUrl(url);
            case "overviewCard"-> existing.setOverviewCardImageUrl(url);
            case "progressCard"-> existing.setProgressCardImageUrl(url);
            default -> throw new IllegalArgumentException("Unknown field: " + field);
        }
        return toDto(repo.save(existing));
    }
}