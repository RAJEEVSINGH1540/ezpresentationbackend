// src/main/java/com/cms/service/ServiceDetailCmsService.java
package com.example.ez.services.service;

import com.example.ez.services.dto.servicedetailpage.*;
import com.example.ez.services.entity.servicedetailpage.*;
import com.example.ez.services.repository.servicedetailpage.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ServiceDetailCmsService {

    private final ServiceHeroRepository heroRepo;
    private final ServiceOverviewRepository overviewRepo;
    private final ServiceStatRepository statRepo;
    private final ServiceModuleRepository   moduleRepo;
    private final ServiceBenefitRepository  benefitRepo;
    private final ServiceFeatureRepository  featureRepo;
    private final ServiceTechSpecRepository techSpecRepo;
    private final ServiceCtaRepository ctaRepo;
    private final ServiceRelatedRepository  relatedRepo;

    // ── GET FULL ────────────────────────────────────────────────────
    public ServiceFullDto getFull(String serviceId) {

        ServiceHeroDto hero = heroRepo.findByServiceId(serviceId)
            .map(h -> ServiceHeroDto.builder()
                .serviceId(h.getServiceId())
                .title(h.getTitle())
                .subtitle(h.getSubtitle())
                .tagline(h.getTagline())
                .description(h.getDescription())
                .heroImage(h.getHeroImage())
                .color(h.getColor())
                .tag(h.getTag())
                .build())
            .orElse(null);

        ServiceOverviewDto overview = overviewRepo.findByServiceId(serviceId)
            .map(o -> new ServiceOverviewDto(o.getOverview()))
            .orElse(new ServiceOverviewDto(""));

        List<ServiceStatDto> stats = statRepo
            .findByServiceIdOrderBySortOrder(serviceId)
            .stream()
            .map(s -> ServiceStatDto.builder()
                .id(s.getId())
                .value(s.getValue())
                .label(s.getLabel())
                .sortOrder(s.getSortOrder())
                .build())
            .collect(Collectors.toList());

        List<ServiceModuleDto> modules = moduleRepo
            .findByServiceIdOrderBySortOrder(serviceId)
            .stream()
            .map(m -> ServiceModuleDto.builder()
                .id(m.getId())
                .icon(m.getIcon())
                .title(m.getTitle())
                .desc(m.getDesc())
                .sortOrder(m.getSortOrder())
                .build())
            .collect(Collectors.toList());

        List<ServiceBenefitDto> benefits = benefitRepo
            .findByServiceIdOrderBySortOrder(serviceId)
            .stream()
            .map(b -> ServiceBenefitDto.builder()
                .id(b.getId())
                .title(b.getTitle())
                .desc(b.getDesc())
                .image(b.getImage())
                .sortOrder(b.getSortOrder())
                .build())
            .collect(Collectors.toList());

        List<ServiceFeatureDto> features = featureRepo
            .findByServiceIdOrderBySortOrder(serviceId)
            .stream()
            .map(f -> ServiceFeatureDto.builder()
                .id(f.getId())
                .feature(f.getFeature())
                .sortOrder(f.getSortOrder())
                .build())
            .collect(Collectors.toList());

        List<ServiceTechSpecDto> techSpecs = techSpecRepo
            .findByServiceIdOrderBySortOrder(serviceId)
            .stream()
            .map(t -> ServiceTechSpecDto.builder()
                .id(t.getId())
                .label(t.getLabel())
                .value(t.getValue())
                .sortOrder(t.getSortOrder())
                .build())
            .collect(Collectors.toList());

        ServiceCtaDto cta = ctaRepo.findByServiceId(serviceId)
            .map(c -> ServiceCtaDto.builder()
                .badgeText(c.getBadgeText())
                .heading(c.getHeading())
                .subText(c.getSubText())
                .buttonLabel(c.getButtonLabel())
                .trustPoints(Arrays.asList(
                    c.getTrustPoint1(),
                    c.getTrustPoint2(),
                    c.getTrustPoint3(),
                    c.getTrustPoint4()))
                .roiLabel(c.getRoiLabel())
                .roiValue(c.getRoiValue())
                .goLiveLabel(c.getGoLiveLabel())
                .goLiveValue(c.getGoLiveValue())
                .goLiveSubtext(c.getGoLiveSubtext())
                .build())
            .orElse(null);

        List<ServiceRelatedDto> related = relatedRepo
            .findByServiceIdOrderBySortOrder(serviceId)
            .stream()
            .map(r -> ServiceRelatedDto.builder()
                .id(r.getId())
                .relatedServiceId(r.getRelatedServiceId())
                .sortOrder(r.getSortOrder())
                .build())
            .collect(Collectors.toList());

        return ServiceFullDto.builder()
            .hero(hero).overview(overview).stats(stats)
            .modules(modules).benefits(benefits).features(features)
            .techSpecs(techSpecs).cta(cta).related(related)
            .build();
    }

    // ── SAVE HERO ───────────────────────────────────────────────────
    @Transactional
    public ServiceHeroDto saveHero(String serviceId, ServiceHeroDto dto) {
        ServiceHero hero = heroRepo.findByServiceId(serviceId)
            .orElse(ServiceHero.builder().serviceId(serviceId).build());
        hero.setTitle(dto.getTitle());
        hero.setSubtitle(dto.getSubtitle());
        hero.setTagline(dto.getTagline());
        hero.setDescription(dto.getDescription());
        hero.setHeroImage(dto.getHeroImage());
        hero.setColor(dto.getColor());
        hero.setTag(dto.getTag());
        ServiceHero saved = heroRepo.save(hero);
        return ServiceHeroDto.builder()
            .serviceId(saved.getServiceId()).title(saved.getTitle())
            .subtitle(saved.getSubtitle()).tagline(saved.getTagline())
            .description(saved.getDescription()).heroImage(saved.getHeroImage())
            .color(saved.getColor()).tag(saved.getTag()).build();
    }

    // ── SAVE OVERVIEW ───────────────────────────────────────────────
    @Transactional
    public ServiceOverviewDto saveOverview(String serviceId, ServiceOverviewDto dto) {
        ServiceOverview ov = overviewRepo.findByServiceId(serviceId)
            .orElse(ServiceOverview.builder().serviceId(serviceId).build());
        ov.setOverview(dto.getOverview());
        overviewRepo.save(ov);
        return dto;
    }

    // ── SAVE STATS ──────────────────────────────────────────────────
    @Transactional
    public List<ServiceStatDto> saveStats(String serviceId, List<ServiceStatDto> dtos) {
        statRepo.deleteByServiceId(serviceId);
        List<ServiceStat> saved = IntStream.range(0, dtos.size()).mapToObj(i -> {
            ServiceStatDto d = dtos.get(i);
            return statRepo.save(ServiceStat.builder()
                .serviceId(serviceId)
                .value(d.getValue())
                .label(d.getLabel())
                .sortOrder(i)
                .build());
        }).collect(Collectors.toList());
        return saved.stream().map(s -> ServiceStatDto.builder()
            .id(s.getId()).value(s.getValue())
            .label(s.getLabel()).sortOrder(s.getSortOrder()).build())
            .collect(Collectors.toList());
    }

    // ── SAVE MODULES ────────────────────────────────────────────────
    @Transactional
    public List<ServiceModuleDto> saveModules(String serviceId, List<ServiceModuleDto> dtos) {
        moduleRepo.deleteByServiceId(serviceId);
        List<ServiceModule> saved = IntStream.range(0, dtos.size()).mapToObj(i -> {
            ServiceModuleDto d = dtos.get(i);
            return moduleRepo.save(ServiceModule.builder()
                .serviceId(serviceId)
                .icon(d.getIcon())
                .title(d.getTitle())
                .desc(d.getDesc())
                .sortOrder(i)
                .build());
        }).collect(Collectors.toList());
        return saved.stream().map(m -> ServiceModuleDto.builder()
            .id(m.getId()).icon(m.getIcon()).title(m.getTitle())
            .desc(m.getDesc()).sortOrder(m.getSortOrder()).build())
            .collect(Collectors.toList());
    }

    // ── SAVE BENEFITS ───────────────────────────────────────────────
    @Transactional
    public List<ServiceBenefitDto> saveBenefits(String serviceId, List<ServiceBenefitDto> dtos) {
        benefitRepo.deleteByServiceId(serviceId);
        List<ServiceBenefit> saved = IntStream.range(0, dtos.size()).mapToObj(i -> {
            ServiceBenefitDto d = dtos.get(i);
            return benefitRepo.save(ServiceBenefit.builder()
                .serviceId(serviceId)
                .title(d.getTitle())
                .desc(d.getDesc())
                .image(d.getImage())
                .sortOrder(i)
                .build());
        }).collect(Collectors.toList());
        return saved.stream().map(b -> ServiceBenefitDto.builder()
            .id(b.getId()).title(b.getTitle()).desc(b.getDesc())
            .image(b.getImage()).sortOrder(b.getSortOrder()).build())
            .collect(Collectors.toList());
    }

    // ── SAVE FEATURES ───────────────────────────────────────────────
    @Transactional
    public List<ServiceFeatureDto> saveFeatures(String serviceId, List<ServiceFeatureDto> dtos) {
        featureRepo.deleteByServiceId(serviceId);
        List<ServiceFeature> saved = IntStream.range(0, dtos.size()).mapToObj(i -> {
            ServiceFeatureDto d = dtos.get(i);
            return featureRepo.save(ServiceFeature.builder()
                .serviceId(serviceId)
                .feature(d.getFeature())
                .sortOrder(i)
                .build());
        }).collect(Collectors.toList());
        return saved.stream().map(f -> ServiceFeatureDto.builder()
            .id(f.getId()).feature(f.getFeature())
            .sortOrder(f.getSortOrder()).build())
            .collect(Collectors.toList());
    }

    // ── SAVE TECH SPECS ─────────────────────────────────────────────
    @Transactional
    public List<ServiceTechSpecDto> saveTechSpecs(String serviceId, List<ServiceTechSpecDto> dtos) {
        techSpecRepo.deleteByServiceId(serviceId);
        List<ServiceTechSpec> saved = IntStream.range(0, dtos.size()).mapToObj(i -> {
            ServiceTechSpecDto d = dtos.get(i);
            return techSpecRepo.save(ServiceTechSpec.builder()
                .serviceId(serviceId)
                .label(d.getLabel())
                .value(d.getValue())
                .sortOrder(i)
                .build());
        }).collect(Collectors.toList());
        return saved.stream().map(t -> ServiceTechSpecDto.builder()
            .id(t.getId()).label(t.getLabel()).value(t.getValue())
            .sortOrder(t.getSortOrder()).build())
            .collect(Collectors.toList());
    }

    // ── SAVE CTA ────────────────────────────────────────────────────
    @Transactional
    public ServiceCtaDto saveCta(String serviceId, ServiceCtaDto dto) {
        ServiceCta cta = ctaRepo.findByServiceId(serviceId)
            .orElse(ServiceCta.builder().serviceId(serviceId).build());
        cta.setBadgeText(dto.getBadgeText());
        cta.setHeading(dto.getHeading());
        cta.setSubText(dto.getSubText());
        cta.setButtonLabel(dto.getButtonLabel());
        List<String> tp = dto.getTrustPoints() != null ? dto.getTrustPoints() : List.of();
        cta.setTrustPoint1(tp.size() > 0 ? tp.get(0) : null);
        cta.setTrustPoint2(tp.size() > 1 ? tp.get(1) : null);
        cta.setTrustPoint3(tp.size() > 2 ? tp.get(2) : null);
        cta.setTrustPoint4(tp.size() > 3 ? tp.get(3) : null);
        cta.setRoiLabel(dto.getRoiLabel());
        cta.setRoiValue(dto.getRoiValue());
        cta.setGoLiveLabel(dto.getGoLiveLabel());
        cta.setGoLiveValue(dto.getGoLiveValue());
        cta.setGoLiveSubtext(dto.getGoLiveSubtext());
        ctaRepo.save(cta);
        return dto;
    }

    // ── SAVE RELATED ────────────────────────────────────────────────
    @Transactional
    public List<ServiceRelatedDto> saveRelated(String serviceId, List<ServiceRelatedDto> dtos) {
        relatedRepo.deleteByServiceId(serviceId);
        List<ServiceRelated> saved = IntStream.range(0, dtos.size()).mapToObj(i -> {
            ServiceRelatedDto d = dtos.get(i);
            return relatedRepo.save(ServiceRelated.builder()
                .serviceId(serviceId)
                .relatedServiceId(d.getRelatedServiceId())
                .sortOrder(i)
                .build());
        }).collect(Collectors.toList());
        return saved.stream().map(r -> ServiceRelatedDto.builder()
            .id(r.getId()).relatedServiceId(r.getRelatedServiceId())
            .sortOrder(r.getSortOrder()).build())
            .collect(Collectors.toList());
    }

    // ── GET ALL SERVICE IDS ─────────────────────────────────────────
    public List<String> getAllServiceIds() {
        return heroRepo.findAll().stream()
            .map(ServiceHero::getServiceId)
            .collect(Collectors.toList());
    }

    // ── CHECK EXISTS ─────────────────────────────────────────────────
    public boolean serviceExists(String serviceId) {
        return heroRepo.findByServiceId(serviceId).isPresent();
    }
    // Add to ServiceDetailCmsService.java

    @Transactional
    public void deleteService(String serviceId) {
        heroRepo.findByServiceId(serviceId).ifPresent(heroRepo::delete);
        overviewRepo.findByServiceId(serviceId).ifPresent(overviewRepo::delete);
        statRepo.deleteByServiceId(serviceId);
        moduleRepo.deleteByServiceId(serviceId);
        benefitRepo.deleteByServiceId(serviceId);
        featureRepo.deleteByServiceId(serviceId);
        techSpecRepo.deleteByServiceId(serviceId);
        ctaRepo.findByServiceId(serviceId).ifPresent(ctaRepo::delete);
        relatedRepo.deleteByServiceId(serviceId);
    }
}