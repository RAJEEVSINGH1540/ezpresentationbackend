package com.example.ez.homepage.whychoose.service;

import com.example.ez.homepage.whychoose.dto.WhyChooseBenefitDto;
import com.example.ez.homepage.whychoose.dto.WhyChooseSectionDto;
import com.example.ez.homepage.whychoose.entity.WhyChooseBenefit;
import com.example.ez.homepage.whychoose.entity.WhyChooseSection;
import com.example.ez.homepage.whychoose.repository.WhyChooseBenefitRepository;
import com.example.ez.homepage.whychoose.repository.WhyChooseSectionRepository;
import com.example.ez.services.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WhyChooseService {

    private final WhyChooseSectionRepository sectionRepo;
    private final WhyChooseBenefitRepository benefitRepo;
    private final ImageUploadService imageUploadService;

    // ── Mapping ─────────────────────────────────────────────────
    private WhyChooseSectionDto toSectionDto(WhyChooseSection e) {
        return WhyChooseSectionDto.builder()
                .id(e.getId())
                .eyebrowText(e.getEyebrowText())
                .headline(e.getHeadline())
                .dashboardImageUrl(e.getDashboardImageUrl())
                .benefits(benefitRepo.findAllByOrderBySortOrderAsc()
                        .stream().map(this::toBenefitDto).collect(Collectors.toList()))
                .build();
    }

    private WhyChooseBenefitDto toBenefitDto(WhyChooseBenefit e) {
        return WhyChooseBenefitDto.builder()
                .id(e.getId())
                .sortOrder(e.getSortOrder())
                .numLabel(e.getNumLabel())
                .title(e.getTitle())
                .description(e.getDescription())
                .build();
    }

    // ── Section CRUD ─────────────────────────────────────────────
    public WhyChooseSectionDto getSection() {
        return sectionRepo.findAll().stream().findFirst()
                .map(this::toSectionDto)
                .orElseThrow(() -> new RuntimeException("WhyChooseSection not initialized"));
    }

    public WhyChooseSectionDto updateSection(Long id, WhyChooseSectionDto dto) {
        WhyChooseSection existing = sectionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Section not found: " + id));
        existing.setEyebrowText(dto.getEyebrowText());
        existing.setHeadline(dto.getHeadline());
        if (dto.getDashboardImageUrl() != null) existing.setDashboardImageUrl(dto.getDashboardImageUrl());
        return toSectionDto(sectionRepo.save(existing));
    }

    public WhyChooseSectionDto uploadDashboardImage(Long id, MultipartFile file) throws IOException {
        WhyChooseSection existing = sectionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Section not found: " + id));
        existing.setDashboardImageUrl(imageUploadService.upload(file));
        return toSectionDto(sectionRepo.save(existing));
    }

    // ── Benefit CRUD ─────────────────────────────────────────────
    public List<WhyChooseBenefitDto> getAllBenefits() {
        return benefitRepo.findAllByOrderBySortOrderAsc()
                .stream().map(this::toBenefitDto).collect(Collectors.toList());
    }

    public WhyChooseBenefitDto getBenefit(Long id) {
        return benefitRepo.findById(id).map(this::toBenefitDto)
                .orElseThrow(() -> new RuntimeException("Benefit not found: " + id));
    }

    public WhyChooseBenefitDto createBenefit(WhyChooseBenefitDto dto) {
        WhyChooseBenefit entity = WhyChooseBenefit.builder()
                .sortOrder(dto.getSortOrder() != null ? dto.getSortOrder() : (int) benefitRepo.count() + 1)
                .numLabel(dto.getNumLabel())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
        return toBenefitDto(benefitRepo.save(entity));
    }

    public WhyChooseBenefitDto updateBenefit(Long id, WhyChooseBenefitDto dto) {
        WhyChooseBenefit existing = benefitRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Benefit not found: " + id));
        existing.setSortOrder(dto.getSortOrder());
        existing.setNumLabel(dto.getNumLabel());
        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        return toBenefitDto(benefitRepo.save(existing));
    }

    public void deleteBenefit(Long id) {
        benefitRepo.deleteById(id);
    }
}