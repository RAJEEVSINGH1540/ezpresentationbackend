package com.example.ez.homepage.HeroSection.controller;

import com.example.ez.homepage.HeroSection.dto.HeroSectionDto;
import com.example.ez.homepage.HeroSection.service.HeroSectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/homepage/hero")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class HeroSectionController {

    private final HeroSectionService service;

    @GetMapping
    public ResponseEntity<List<HeroSectionDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeroSectionDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<HeroSectionDto> create(@RequestBody HeroSectionDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HeroSectionDto> update(
            @PathVariable Long id,
            @RequestBody HeroSectionDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/upload-image")
    public ResponseEntity<HeroSectionDto> uploadImage(
            @PathVariable Long id,
            @RequestParam String field,
            @RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.ok(service.uploadImage(id, field, file));
    }
}