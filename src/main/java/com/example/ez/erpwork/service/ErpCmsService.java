package com.example.ez.erpwork.service;

import com.example.ez.erpwork.dto.*;
import com.example.ez.erpwork.entity.*;
import com.example.ez.erpwork.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ErpCmsService {

    private final ErpServiceRepository serviceRepo;
    private final ErpHeroSectionRepository heroRepo;
    private final ErpBISectionRepository biRepo;
    private final ErpCustomerSupportRepository csRepo;
    private final ErpFaqItemRepository faqRepo;
    private final ErpFeatureTabRepository tabRepo;
    private final ErpFeatureTabItemRepository tabItemRepo;
    private final ErpProductRepository productRepo;
    private final ErpProductFeatureRepository productFeatureRepo;

    @Value("${cms.upload.dir:uploads}")
    private String uploadDir;

    // ══════════════════════════════════════════════════════════════════════
    // IMAGE UPLOAD
    // ══════════════════════════════════════════════════════════════════════

    public String uploadImage(MultipartFile file, String subfolder) throws IOException {
        Path dir = Paths.get(uploadDir, "erp", subfolder);
        Files.createDirectories(dir);

        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename()
                .replaceAll("[^a-zA-Z0-9._-]", "_");

        Path target = dir.resolve(filename);
        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

        return "/uploads/erp/" + subfolder + "/" + filename;
    }

    // ══════════════════════════════════════════════════════════════════════
    // ERP SERVICE CRUD
    // ══════════════════════════════════════════════════════════════════════

    @Transactional(readOnly = true)
    public List<ErpServiceDTO> getAllServices() {
        return serviceRepo.findAllByOrderByDisplayOrderAsc()
                .stream()
                .map(this::toServiceDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ErpServiceDTO> getActiveServices() {
        return serviceRepo.findByActiveOrderByDisplayOrderAsc(true)
                .stream()
                .map(this::toServiceDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ErpServiceFullDTO getServiceFull(Long id) {
        ErpService s = serviceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found: " + id));
        return toFullDTO(s);
    }

    @Transactional(readOnly = true)
    public ErpServiceFullDTO getServiceFullBySlug(String slug) {
        ErpService s = serviceRepo.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Service not found: " + slug));
        return toFullDTO(s);
    }

    public ErpServiceDTO createService(ErpServiceDTO dto) {
        if (serviceRepo.existsBySlug(dto.getSlug())) {
            throw new RuntimeException("Slug already exists: " + dto.getSlug());
        }
        ErpService entity = ErpService.builder()
                .name(dto.getName())
                .slug(dto.getSlug())
                .description(dto.getDescription())
                .iconUrl(dto.getIconUrl())
                .cardImageUrl(dto.getCardImageUrl())
                .active(dto.isActive())
                .displayOrder(dto.getDisplayOrder())
                .build();
        return toServiceDTO(serviceRepo.save(entity));
    }

    public ErpServiceDTO updateService(Long id, ErpServiceDTO dto) {
        ErpService entity = serviceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found: " + id));
        entity.setName(dto.getName());
        entity.setSlug(dto.getSlug());
        entity.setDescription(dto.getDescription());
        entity.setIconUrl(dto.getIconUrl());
        entity.setCardImageUrl(dto.getCardImageUrl());
        entity.setActive(dto.isActive());
        entity.setDisplayOrder(dto.getDisplayOrder());
        return toServiceDTO(serviceRepo.save(entity));
    }

    public void deleteService(Long id) {
        if (!serviceRepo.existsById(id)) {
            throw new RuntimeException("Service not found: " + id);
        }
        serviceRepo.deleteById(id);
    }

    // ══════════════════════════════════════════════════════════════════════
    // HERO SECTION CRUD
    // ══════════════════════════════════════════════════════════════════════

    @Transactional(readOnly = true)
    public ErpHeroSectionDTO getHeroSection(Long serviceId) {
        return heroRepo.findByErpServiceId(serviceId)
                .map(this::toHeroDTO)
                .orElse(null);
    }

    public ErpHeroSectionDTO saveHeroSection(Long serviceId, ErpHeroSectionDTO dto) {
        ErpService service = serviceRepo.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        ErpHeroSection entity = heroRepo.findByErpServiceId(serviceId)
                .orElse(ErpHeroSection.builder().erpService(service).build());

        entity.setBadgeText(dto.getBadgeText());
        entity.setHeadline(dto.getHeadline());
        entity.setDescription(dto.getDescription());
        entity.setCtaButtonText(dto.getCtaButtonText());
        entity.setCtaButtonLink(dto.getCtaButtonLink());
        if (dto.getDashboardImageUrl() != null)
            entity.setDashboardImageUrl(dto.getDashboardImageUrl());

        return toHeroDTO(heroRepo.save(entity));
    }

    // ══════════════════════════════════════════════════════════════════════
    // BUSINESS INTELLIGENCE SECTION CRUD
    // ══════════════════════════════════════════════════════════════════════

    @Transactional(readOnly = true)
    public ErpBISectionDTO getBISection(Long serviceId) {
        return biRepo.findByErpServiceId(serviceId)
                .map(this::toBIDTO)
                .orElse(null);
    }

    public ErpBISectionDTO saveBISection(Long serviceId, ErpBISectionDTO dto) {
        ErpService service = serviceRepo.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        ErpBusinessIntelligenceSection entity = biRepo.findByErpServiceId(serviceId)
                .orElse(ErpBusinessIntelligenceSection.builder().erpService(service).build());

        entity.setHeadingPart1(dto.getHeadingPart1());
        entity.setHeadingHighlight(dto.getHeadingHighlight());
        entity.setHeadingPart2(dto.getHeadingPart2());
        entity.setParagraph1(dto.getParagraph1());
        entity.setParagraph2(dto.getParagraph2());
        entity.setParagraph3(dto.getParagraph3());
        entity.setCtaButtonText(dto.getCtaButtonText());
        entity.setCtaButtonLink(dto.getCtaButtonLink());
        if (dto.getOverviewCardImageUrl() != null)
            entity.setOverviewCardImageUrl(dto.getOverviewCardImageUrl());
        if (dto.getProgressCardImageUrl() != null)
            entity.setProgressCardImageUrl(dto.getProgressCardImageUrl());
        if (dto.getDashboardImageUrl() != null)
            entity.setDashboardImageUrl(dto.getDashboardImageUrl());

        return toBIDTO(biRepo.save(entity));
    }

    // ══════════════════════════════════════════════════════════════════════
    // CUSTOMER SUPPORT SECTION CRUD
    // ══════════════════════════════════════════════════════════════════════

    @Transactional(readOnly = true)
    public ErpCustomerSupportDTO getCSSection(Long serviceId) {
        return csRepo.findByErpServiceId(serviceId)
                .map(this::toCSDTO)
                .orElse(null);
    }

    public ErpCustomerSupportDTO saveCSSection(Long serviceId, ErpCustomerSupportDTO dto) {
        ErpService service = serviceRepo.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        ErpCustomerSupportSection entity = csRepo.findByErpServiceId(serviceId)
                .orElse(ErpCustomerSupportSection.builder().erpService(service).build());

        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setCtaButtonText(dto.getCtaButtonText());
        entity.setCtaButtonLink(dto.getCtaButtonLink());
        entity.setPortalBadgeInitial(dto.getPortalBadgeInitial());
        entity.setPortalBadgeLabel(dto.getPortalBadgeLabel());
        if (dto.getDashboardImageUrl() != null)
            entity.setDashboardImageUrl(dto.getDashboardImageUrl());

        return toCSDTO(csRepo.save(entity));
    }

    // ══════════════════════════════════════════════════════════════════════
    // FAQ CRUD
    // ══════════════════════════════════════════════════════════════════════

    @Transactional(readOnly = true)
    public List<ErpFaqItemDTO> getFaqs(Long serviceId) {
        return faqRepo.findByErpServiceIdOrderByDisplayOrderAsc(serviceId)
                .stream().map(this::toFaqDTO).collect(Collectors.toList());
    }

    // createFaq()
    public ErpFaqItemDTO createFaq(Long serviceId, ErpFaqItemDTO dto) {
        ErpService service = serviceRepo.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        ErpFaqItem entity = ErpFaqItem.builder()
                .erpService(service)
                .question(dto.getQuestion())
                .answer(dto.getAnswer())
                .displayOrder(dto.getDisplayOrder())
                .faqColumn(dto.getFaqColumn() != null ? dto.getFaqColumn() : "left") // ← fixed
                .sectionBadgeText(dto.getSectionBadgeText())
                .sectionTitle(dto.getSectionTitle())
                .sectionDescription(dto.getSectionDescription())
                .build();

        return toFaqDTO(faqRepo.save(entity));
    }

    // updateFaq()
    public ErpFaqItemDTO updateFaq(Long faqId, ErpFaqItemDTO dto) {
        ErpFaqItem entity = faqRepo.findById(faqId)
                .orElseThrow(() -> new RuntimeException("FAQ not found"));

        entity.setQuestion(dto.getQuestion());
        entity.setAnswer(dto.getAnswer());
        entity.setDisplayOrder(dto.getDisplayOrder());
        entity.setFaqColumn(dto.getFaqColumn() != null ? dto.getFaqColumn() : "left"); // ← fixed
        entity.setSectionBadgeText(dto.getSectionBadgeText());
        entity.setSectionTitle(dto.getSectionTitle());
        entity.setSectionDescription(dto.getSectionDescription());

        return toFaqDTO(faqRepo.save(entity));
    }

    public void deleteFaq(Long faqId) {
        faqRepo.deleteById(faqId);
    }

    // ══════════════════════════════════════════════════════════════════════
    // FEATURE TABS CRUD
    // ══════════════════════════════════════════════════════════════════════

    @Transactional(readOnly = true)
    public List<ErpFeatureTabDTO> getFeatureTabs(Long serviceId) {
        return tabRepo.findByErpServiceIdOrderByDisplayOrderAsc(serviceId)
                .stream().map(this::toTabDTO).collect(Collectors.toList());
    }

    public ErpFeatureTabDTO createFeatureTab(Long serviceId, ErpFeatureTabDTO dto) {
        ErpService service = serviceRepo.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        ErpFeatureTab tab = ErpFeatureTab.builder()
                .erpService(service)
                .tabId(dto.getTabId())
                .label(dto.getLabel())
                .title(dto.getTitle())
                .description1(dto.getDescription1())
                .description2(dto.getDescription2())
                .featuresLabel(dto.getFeaturesLabel())
                .imageUrl(dto.getImageUrl())
                .imageAlt(dto.getImageAlt())
                .usesDashboard(dto.isUsesDashboard())
                .displayOrder(dto.getDisplayOrder())
                .build();

        ErpFeatureTab saved = tabRepo.save(tab);

        if (dto.getFeatureItems() != null) {
            saveTabItems(saved, dto.getFeatureItems());
        }

        return toTabDTO(tabRepo.findById(saved.getId()).orElseThrow());
    }

    public ErpFeatureTabDTO updateFeatureTab(Long tabId, ErpFeatureTabDTO dto) {
        ErpFeatureTab tab = tabRepo.findById(tabId)
                .orElseThrow(() -> new RuntimeException("Tab not found"));

        tab.setTabId(dto.getTabId());
        tab.setLabel(dto.getLabel());
        tab.setTitle(dto.getTitle());
        tab.setDescription1(dto.getDescription1());
        tab.setDescription2(dto.getDescription2());
        tab.setFeaturesLabel(dto.getFeaturesLabel());
        tab.setImageAlt(dto.getImageAlt());
        tab.setUsesDashboard(dto.isUsesDashboard());
        tab.setDisplayOrder(dto.getDisplayOrder());
        if (dto.getImageUrl() != null) tab.setImageUrl(dto.getImageUrl());

        tabItemRepo.deleteByFeatureTabId(tabId);
        ErpFeatureTab saved = tabRepo.save(tab);

        if (dto.getFeatureItems() != null) {
            saveTabItems(saved, dto.getFeatureItems());
        }

        return toTabDTO(tabRepo.findById(saved.getId()).orElseThrow());
    }

    public void deleteFeatureTab(Long tabId) {
        tabRepo.deleteById(tabId);
    }

    private void saveTabItems(ErpFeatureTab tab, List<ErpFeatureTabItemDTO> items) {
        List<ErpFeatureTabItem> entities = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            ErpFeatureTabItemDTO d = items.get(i);
            entities.add(ErpFeatureTabItem.builder()
                    .featureTab(tab)
                    .leftText(d.getLeftText())
                    .rightText(d.getRightText())
                    .displayOrder(i)
                    .build());
        }
        tabItemRepo.saveAll(entities);
    }

    // ══════════════════════════════════════════════════════════════════════
    // PRODUCTS CRUD
    // ══════════════════════════════════════════════════════════════════════

    @Transactional(readOnly = true)
    public List<ErpProductDTO> getProducts(Long serviceId) {
        return productRepo.findByErpServiceIdOrderByDisplayOrderAsc(serviceId)
                .stream().map(this::toProductDTO).collect(Collectors.toList());
    }

    public ErpProductDTO createProduct(Long serviceId, ErpProductDTO dto) {
        ErpService service = serviceRepo.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        ErpProduct product = ErpProduct.builder()
                .erpService(service)
                .title(dto.getTitle())
                .description(dto.getDescription())
                .ctaButtonText(dto.getCtaButtonText())
                .ctaButtonLink(dto.getCtaButtonLink())
                .displayOrder(dto.getDisplayOrder())
                .sectionBadgeText(dto.getSectionBadgeText())
                .sectionTitle(dto.getSectionTitle())
                .sectionDescription(dto.getSectionDescription())
                .build();

        ErpProduct saved = productRepo.save(product);

        if (dto.getFeatures() != null) {
            saveProductFeatures(saved, dto.getFeatures());
        }

        return toProductDTO(productRepo.findById(saved.getId()).orElseThrow());
    }

    public ErpProductDTO updateProduct(Long productId, ErpProductDTO dto) {
        ErpProduct product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setTitle(dto.getTitle());
        product.setDescription(dto.getDescription());
        product.setCtaButtonText(dto.getCtaButtonText());
        product.setCtaButtonLink(dto.getCtaButtonLink());
        product.setDisplayOrder(dto.getDisplayOrder());
        product.setSectionBadgeText(dto.getSectionBadgeText());
        product.setSectionTitle(dto.getSectionTitle());
        product.setSectionDescription(dto.getSectionDescription());

        productFeatureRepo.deleteByErpProductId(productId);
        ErpProduct saved = productRepo.save(product);

        if (dto.getFeatures() != null) {
            saveProductFeatures(saved, dto.getFeatures());
        }

        return toProductDTO(productRepo.findById(saved.getId()).orElseThrow());
    }

    public void deleteProduct(Long productId) {
        productRepo.deleteById(productId);
    }

    private void saveProductFeatures(ErpProduct product,
                                     List<ErpProductFeatureDTO> features) {
        List<ErpProductFeature> entities = new ArrayList<>();
        for (int i = 0; i < features.size(); i++) {
            entities.add(ErpProductFeature.builder()
                    .erpProduct(product)
                    .featureText(features.get(i).getFeatureText())
                    .displayOrder(i)
                    .build());
        }
        productFeatureRepo.saveAll(entities);
    }

    // ══════════════════════════════════════════════════════════════════════
    // MAPPERS
    // ══════════════════════════════════════════════════════════════════════

    private ErpServiceDTO toServiceDTO(ErpService e) {
        return ErpServiceDTO.builder()
                .id(e.getId()).name(e.getName()).slug(e.getSlug())
                .description(e.getDescription()).iconUrl(e.getIconUrl())
                .cardImageUrl(e.getCardImageUrl()).active(e.isActive())
                .displayOrder(e.getDisplayOrder())
                .createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .build();
    }

    private ErpServiceFullDTO toFullDTO(ErpService e) {
        return ErpServiceFullDTO.builder()
                .id(e.getId()).name(e.getName()).slug(e.getSlug())
                .description(e.getDescription()).iconUrl(e.getIconUrl())
                .cardImageUrl(e.getCardImageUrl()).active(e.isActive())
                .displayOrder(e.getDisplayOrder())
                .createdAt(e.getCreatedAt()).updatedAt(e.getUpdatedAt())
                .heroSection(heroRepo.findByErpServiceId(e.getId()).map(this::toHeroDTO).orElse(null))
                .businessIntelligenceSection(biRepo.findByErpServiceId(e.getId()).map(this::toBIDTO).orElse(null))
                .customerSupportSection(csRepo.findByErpServiceId(e.getId()).map(this::toCSDTO).orElse(null))
                .faqItems(faqRepo.findByErpServiceIdOrderByDisplayOrderAsc(e.getId()).stream().map(this::toFaqDTO).collect(Collectors.toList()))
                .featureTabs(tabRepo.findByErpServiceIdOrderByDisplayOrderAsc(e.getId()).stream().map(this::toTabDTO).collect(Collectors.toList()))
                .products(productRepo.findByErpServiceIdOrderByDisplayOrderAsc(e.getId()).stream().map(this::toProductDTO).collect(Collectors.toList()))
                .build();
    }

    private ErpHeroSectionDTO toHeroDTO(ErpHeroSection e) {
        return ErpHeroSectionDTO.builder()
                .id(e.getId()).erpServiceId(e.getErpService().getId())
                .badgeText(e.getBadgeText()).headline(e.getHeadline())
                .description(e.getDescription()).ctaButtonText(e.getCtaButtonText())
                .ctaButtonLink(e.getCtaButtonLink()).dashboardImageUrl(e.getDashboardImageUrl())
                .build();
    }

    private ErpBISectionDTO toBIDTO(ErpBusinessIntelligenceSection e) {
        return ErpBISectionDTO.builder()
                .id(e.getId()).erpServiceId(e.getErpService().getId())
                .headingPart1(e.getHeadingPart1()).headingHighlight(e.getHeadingHighlight())
                .headingPart2(e.getHeadingPart2()).paragraph1(e.getParagraph1())
                .paragraph2(e.getParagraph2()).paragraph3(e.getParagraph3())
                .ctaButtonText(e.getCtaButtonText()).ctaButtonLink(e.getCtaButtonLink())
                .overviewCardImageUrl(e.getOverviewCardImageUrl())
                .progressCardImageUrl(e.getProgressCardImageUrl())
                .dashboardImageUrl(e.getDashboardImageUrl())
                .build();
    }

    private ErpCustomerSupportDTO toCSDTO(ErpCustomerSupportSection e) {
        return ErpCustomerSupportDTO.builder()
                .id(e.getId()).erpServiceId(e.getErpService().getId())
                .title(e.getTitle()).description(e.getDescription())
                .ctaButtonText(e.getCtaButtonText()).ctaButtonLink(e.getCtaButtonLink())
                .dashboardImageUrl(e.getDashboardImageUrl())
                .portalBadgeInitial(e.getPortalBadgeInitial())
                .portalBadgeLabel(e.getPortalBadgeLabel())
                .build();
    }

    private ErpFaqItemDTO toFaqDTO(ErpFaqItem e) {
        return ErpFaqItemDTO.builder()
                .id(e.getId())
                .erpServiceId(e.getErpService().getId())
                .question(e.getQuestion())
                .answer(e.getAnswer())
                .displayOrder(e.getDisplayOrder())
                .faqColumn(e.getFaqColumn())            // ← fixed
                .sectionBadgeText(e.getSectionBadgeText())
                .sectionTitle(e.getSectionTitle())
                .sectionDescription(e.getSectionDescription())
                .build();
    }

    private ErpFeatureTabDTO toTabDTO(ErpFeatureTab e) {
        List<ErpFeatureTabItemDTO> items = tabItemRepo
                .findByFeatureTabIdOrderByDisplayOrderAsc(e.getId())
                .stream().map(i -> ErpFeatureTabItemDTO.builder()
                        .id(i.getId()).featureTabId(e.getId())
                        .leftText(i.getLeftText()).rightText(i.getRightText())
                        .displayOrder(i.getDisplayOrder()).build())
                .collect(Collectors.toList());

        return ErpFeatureTabDTO.builder()
                .id(e.getId()).erpServiceId(e.getErpService().getId())
                .tabId(e.getTabId()).label(e.getLabel()).title(e.getTitle())
                .description1(e.getDescription1()).description2(e.getDescription2())
                .featuresLabel(e.getFeaturesLabel()).imageUrl(e.getImageUrl())
                .imageAlt(e.getImageAlt()).usesDashboard(e.isUsesDashboard())
                .displayOrder(e.getDisplayOrder()).featureItems(items)
                .build();
    }

    private ErpProductDTO toProductDTO(ErpProduct e) {
        List<ErpProductFeatureDTO> features = productFeatureRepo
                .findByErpProductIdOrderByDisplayOrderAsc(e.getId())
                .stream().map(f -> ErpProductFeatureDTO.builder()
                        .id(f.getId()).erpProductId(e.getId())
                        .featureText(f.getFeatureText())
                        .displayOrder(f.getDisplayOrder()).build())
                .collect(Collectors.toList());

        return ErpProductDTO.builder()
                .id(e.getId()).erpServiceId(e.getErpService().getId())
                .title(e.getTitle()).description(e.getDescription())
                .ctaButtonText(e.getCtaButtonText()).ctaButtonLink(e.getCtaButtonLink())
                .displayOrder(e.getDisplayOrder())
                .sectionBadgeText(e.getSectionBadgeText())
                .sectionTitle(e.getSectionTitle())
                .sectionDescription(e.getSectionDescription())
                .features(features)
                .build();
    }
}