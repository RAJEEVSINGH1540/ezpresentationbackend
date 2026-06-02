package com.example.ez.homepage.industry.service;

import com.example.ez.homepage.industry.dto.IndustryServiceDto;
import com.example.ez.homepage.industry.entity.IndustryService;
import com.example.ez.homepage.industry.repository.IndustryServiceRepository;
import com.example.ez.services.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IndustryServiceCmsService {

    private final IndustryServiceRepository repo;
    private final ImageUploadService imageUploadService;

    // ── Mapping ─────────────────────────────────────────────────
    private IndustryServiceDto toDto(IndustryService e) {
        return IndustryServiceDto.builder()
                .id(e.getId())
                .sortOrder(e.getSortOrder())
                .label(e.getLabel())
                .subtitle(e.getSubtitle())
                .description(e.getDescription())
                .feature(e.getFeature())
                .imageUrl(e.getImageUrl())
                .active(e.getActive())
                .build();
    }

    private IndustryService toEntity(IndustryServiceDto dto) {
        return IndustryService.builder()
                .sortOrder(dto.getSortOrder())
                .label(dto.getLabel())
                .subtitle(dto.getSubtitle())
                .description(dto.getDescription())
                .feature(dto.getFeature())
                .imageUrl(dto.getImageUrl())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .build();
    }

    // ── CRUD ─────────────────────────────────────────────────────
    public List<IndustryServiceDto> findAll() {
        return repo.findAllByOrderBySortOrderAsc()
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<IndustryServiceDto> findActive() {
        return repo.findByActiveTrueOrderBySortOrderAsc()
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    public IndustryServiceDto findById(Long id) {
        return repo.findById(id).map(this::toDto)
                .orElseThrow(() -> new RuntimeException("IndustryService not found: " + id));
    }

    public IndustryServiceDto create(IndustryServiceDto dto) {
        if (dto.getSortOrder() == null) {
            dto.setSortOrder((int) repo.count() + 1);
        }
        return toDto(repo.save(toEntity(dto)));
    }

    public IndustryServiceDto update(Long id, IndustryServiceDto dto) {
        IndustryService existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("IndustryService not found: " + id));
        existing.setSortOrder(dto.getSortOrder());
        existing.setLabel(dto.getLabel());
        existing.setSubtitle(dto.getSubtitle());
        existing.setDescription(dto.getDescription());
        existing.setFeature(dto.getFeature());
        existing.setActive(dto.getActive());
        if (dto.getImageUrl() != null) existing.setImageUrl(dto.getImageUrl());
        return toDto(repo.save(existing));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public IndustryServiceDto uploadImage(Long id, MultipartFile file) throws IOException {
        IndustryService existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("IndustryService not found: " + id));
        existing.setImageUrl(imageUploadService.upload(file));
        return toDto(repo.save(existing));
    }
}