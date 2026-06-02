package com.example.ez.homepage.trusted.service;

import com.example.ez.homepage.trusted.dto.TrustedBrandDto;
import com.example.ez.homepage.trusted.dto.TrustedSectionDto;
import com.example.ez.homepage.trusted.entity.TrustedBrand;
import com.example.ez.homepage.trusted.entity.TrustedSection;
import com.example.ez.homepage.trusted.repository.TrustedBrandRepository;
import com.example.ez.homepage.trusted.repository.TrustedSectionRepository;
import com.example.ez.services.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrustedBrandsService {

    private final TrustedSectionRepository sectionRepo;
    private final TrustedBrandRepository brandRepo;
    private final ImageUploadService imageUploadService;

    // ── Mapping ─────────────────────────────────────────────────
    private TrustedSectionDto toSectionDto(TrustedSection e) {
        return TrustedSectionDto.builder()
                .id(e.getId())
                .illustrationUrl(e.getIllustrationUrl())
                .mainTagline(e.getMainTagline())
                .subTagline(e.getSubTagline())
                .trustedCountPrefix(e.getTrustedCountPrefix())
                .trustedCountValue(e.getTrustedCountValue())
                .trustedCountSuffix(e.getTrustedCountSuffix())
                .brands(brandRepo.findAllByOrderBySortOrderAsc()
                        .stream().map(this::toBrandDto).collect(Collectors.toList()))
                .build();
    }

    private TrustedBrandDto toBrandDto(TrustedBrand e) {
        return TrustedBrandDto.builder()
                .id(e.getId()).sortOrder(e.getSortOrder())
                .name(e.getName()).logoUrl(e.getLogoUrl()).active(e.getActive())
                .build();
    }

    // ── Section CRUD ─────────────────────────────────────────────
    public TrustedSectionDto getSection() {
        return sectionRepo.findAll().stream().findFirst()
                .map(this::toSectionDto)
                .orElseThrow(() -> new RuntimeException("TrustedSection not initialized"));
    }

    public TrustedSectionDto updateSection(Long id, TrustedSectionDto dto) {
        TrustedSection existing = sectionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Section not found: " + id));
        existing.setMainTagline(dto.getMainTagline());
        existing.setSubTagline(dto.getSubTagline());
        existing.setTrustedCountPrefix(dto.getTrustedCountPrefix());
        existing.setTrustedCountValue(dto.getTrustedCountValue());
        existing.setTrustedCountSuffix(dto.getTrustedCountSuffix());
        if (dto.getIllustrationUrl() != null) existing.setIllustrationUrl(dto.getIllustrationUrl());
        return toSectionDto(sectionRepo.save(existing));
    }

    public TrustedSectionDto uploadIllustration(Long id, MultipartFile file) throws IOException {
        TrustedSection existing = sectionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Section not found: " + id));
        existing.setIllustrationUrl(imageUploadService.upload(file));
        return toSectionDto(sectionRepo.save(existing));
    }

    // ── Brand CRUD ───────────────────────────────────────────────
    public List<TrustedBrandDto> getAllBrands() {
        return brandRepo.findAllByOrderBySortOrderAsc()
                .stream().map(this::toBrandDto).collect(Collectors.toList());
    }

    public TrustedBrandDto getBrand(Long id) {
        return brandRepo.findById(id).map(this::toBrandDto)
                .orElseThrow(() -> new RuntimeException("Brand not found: " + id));
    }

    public TrustedBrandDto createBrand(TrustedBrandDto dto) {
        TrustedBrand brand = TrustedBrand.builder()
                .sortOrder(dto.getSortOrder() != null ? dto.getSortOrder() : (int) brandRepo.count() + 1)
                .name(dto.getName())
                .logoUrl(dto.getLogoUrl())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .build();
        return toBrandDto(brandRepo.save(brand));
    }

    public TrustedBrandDto updateBrand(Long id, TrustedBrandDto dto) {
        TrustedBrand existing = brandRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found: " + id));
        existing.setSortOrder(dto.getSortOrder());
        existing.setName(dto.getName());
        existing.setActive(dto.getActive());
        if (dto.getLogoUrl() != null) existing.setLogoUrl(dto.getLogoUrl());
        return toBrandDto(brandRepo.save(existing));
    }

    public void deleteBrand(Long id) {
        brandRepo.deleteById(id);
    }

    public TrustedBrandDto uploadBrandLogo(Long id, MultipartFile file) throws IOException {
        TrustedBrand existing = brandRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found: " + id));
        existing.setLogoUrl(imageUploadService.upload(file));
        return toBrandDto(brandRepo.save(existing));
    }
}