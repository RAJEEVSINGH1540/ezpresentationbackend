package com.example.ez.services.service;

import com.example.ez.services.dto.ApiResponse;
import com.example.ez.services.dto.servicepage.*;
import com.example.ez.services.entity.servicepage.*;
import com.example.ez.services.repository.servicepage.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CmsSectionService {

    private final HeroSectionRepository heroRepo;
    private final TrustedClientsSectionRepository trustedRepo;
    private final DashboardShowcaseSectionRepository dashboardRepo;
    private final WhyChooseUsSectionRepository whyRepo;
    private final IndustriesSectionRepository industriesRepo;
    private final BenefitsSectionRepository benefitsRepo;
    private final TestimonialsSectionRepository testimonialsRepo;
    private final PricingSectionRepository pricingRepo;
    private final FinalCtaSectionRepository finalCtaRepo;

    // ════════════════════════════════════════════════════════════════
    //  HERO SECTION
    // ════════════════════════════════════════════════════════════════

    public ApiResponse<HeroSectionDTO> getHero() {
        return heroRepo.findAll().stream().findFirst()
                .map(h -> ApiResponse.success("Hero fetched", toHeroDTO(h)))
                .orElse(ApiResponse.error("Hero section not found"));
    }

    public ApiResponse<HeroSectionDTO> getHeroById(Long id) {
        return heroRepo.findById(id)
                .map(h -> ApiResponse.success("Hero fetched", toHeroDTO(h)))
                .orElse(ApiResponse.error("Hero not found"));
    }

    @Transactional
    public ApiResponse<HeroSectionDTO> createHero(HeroSectionDTO dto) {
        HeroSection entity = HeroSection.builder()
                .badge(dto.getBadge())
                .heading(dto.getHeading())
                .headingHighlight(dto.getHeadingHighlight())
                .subheading(dto.getSubheading())
                .ctaPrimaryText(dto.getCtaPrimaryText())
                .ctaSecondaryText(dto.getCtaSecondaryText())
                .badgeColor(dto.getBadgeColor())
                .primaryColor(dto.getPrimaryColor())
                .imageUrl(dto.getImageUrl())
                .isActive(dto.getIsActive() != null ? dto.getIsActive() : true)
                .build();
        return ApiResponse.success("Hero created", toHeroDTO(heroRepo.save(entity)));
    }

    @Transactional
    public ApiResponse<HeroSectionDTO> updateHero(Long id, HeroSectionDTO dto) {
        return heroRepo.findById(id).map(entity -> {
            if (dto.getBadge() != null) entity.setBadge(dto.getBadge());
            if (dto.getHeading() != null) entity.setHeading(dto.getHeading());
            if (dto.getHeadingHighlight() != null) entity.setHeadingHighlight(dto.getHeadingHighlight());
            if (dto.getSubheading() != null) entity.setSubheading(dto.getSubheading());
            if (dto.getCtaPrimaryText() != null) entity.setCtaPrimaryText(dto.getCtaPrimaryText());
            if (dto.getCtaSecondaryText() != null) entity.setCtaSecondaryText(dto.getCtaSecondaryText());
            if (dto.getBadgeColor() != null) entity.setBadgeColor(dto.getBadgeColor());
            if (dto.getPrimaryColor() != null) entity.setPrimaryColor(dto.getPrimaryColor());
            if (dto.getImageUrl() != null) entity.setImageUrl(dto.getImageUrl());
            if (dto.getIsActive() != null) entity.setIsActive(dto.getIsActive());
            return ApiResponse.success("Hero updated", toHeroDTO(heroRepo.save(entity)));
        }).orElse(ApiResponse.error("Hero not found"));
    }

    @Transactional
    public ApiResponse<Void> deleteHero(Long id) {
        if (!heroRepo.existsById(id)) return ApiResponse.error("Hero not found");
        heroRepo.deleteById(id);
        return ApiResponse.success("Hero deleted", null);
    }

    private HeroSectionDTO toHeroDTO(HeroSection e) {
        return HeroSectionDTO.builder()
                .id(e.getId()).badge(e.getBadge()).heading(e.getHeading())
                .headingHighlight(e.getHeadingHighlight()).subheading(e.getSubheading())
                .ctaPrimaryText(e.getCtaPrimaryText()).ctaSecondaryText(e.getCtaSecondaryText())
                .badgeColor(e.getBadgeColor()).primaryColor(e.getPrimaryColor())
                .imageUrl(e.getImageUrl()).isActive(e.getIsActive()).build();
    }

    // ════════════════════════════════════════════════════════════════
    //  TRUSTED CLIENTS SECTION
    // ════════════════════════════════════════════════════════════════

    public ApiResponse<TrustedClientsSectionDTO> getTrustedClients() {
        return trustedRepo.findAll().stream().findFirst()
                .map(e -> ApiResponse.success("TrustedClients fetched", toTrustedDTO(e)))
                .orElse(ApiResponse.error("TrustedClients not found"));
    }

    public ApiResponse<TrustedClientsSectionDTO> getTrustedClientsById(Long id) {
        return trustedRepo.findById(id)
                .map(e -> ApiResponse.success("TrustedClients fetched", toTrustedDTO(e)))
                .orElse(ApiResponse.error("TrustedClients not found"));
    }

    @Transactional
    public ApiResponse<TrustedClientsSectionDTO> createTrustedClients(TrustedClientsSectionDTO dto) {
        TrustedClientsSection entity = TrustedClientsSection.builder()
                .sectionLabel(dto.getSectionLabel())
                .statsJson(dto.getStatsJson())
                .logosJson(dto.getLogosJson())
                .primaryColor(dto.getPrimaryColor())
                .isActive(dto.getIsActive() != null ? dto.getIsActive() : true)
                .build();
        return ApiResponse.success("TrustedClients created", toTrustedDTO(trustedRepo.save(entity)));
    }

    @Transactional
    public ApiResponse<TrustedClientsSectionDTO> updateTrustedClients(Long id, TrustedClientsSectionDTO dto) {
        return trustedRepo.findById(id).map(entity -> {
            if (dto.getSectionLabel() != null) entity.setSectionLabel(dto.getSectionLabel());
            if (dto.getStatsJson() != null) entity.setStatsJson(dto.getStatsJson());
            if (dto.getLogosJson() != null) entity.setLogosJson(dto.getLogosJson());
            if (dto.getPrimaryColor() != null) entity.setPrimaryColor(dto.getPrimaryColor());
            if (dto.getIsActive() != null) entity.setIsActive(dto.getIsActive());
            return ApiResponse.success("TrustedClients updated", toTrustedDTO(trustedRepo.save(entity)));
        }).orElse(ApiResponse.error("TrustedClients not found"));
    }

    @Transactional
    public ApiResponse<Void> deleteTrustedClients(Long id) {
        if (!trustedRepo.existsById(id)) return ApiResponse.error("TrustedClients not found");
        trustedRepo.deleteById(id);
        return ApiResponse.success("TrustedClients deleted", null);
    }

    private TrustedClientsSectionDTO toTrustedDTO(TrustedClientsSection e) {
        return TrustedClientsSectionDTO.builder()
                .id(e.getId()).sectionLabel(e.getSectionLabel())
                .statsJson(e.getStatsJson()).logosJson(e.getLogosJson())
                .primaryColor(e.getPrimaryColor()).isActive(e.getIsActive()).build();
    }

    // ════════════════════════════════════════════════════════════════
    //  DASHBOARD SHOWCASE SECTION
    // ════════════════════════════════════════════════════════════════

    public ApiResponse<DashboardShowcaseSectionDTO> getDashboard() {
        return dashboardRepo.findAll().stream().findFirst()
                .map(e -> ApiResponse.success("Dashboard fetched", toDashboardDTO(e)))
                .orElse(ApiResponse.error("Dashboard not found"));
    }

    public ApiResponse<DashboardShowcaseSectionDTO> getDashboardById(Long id) {
        return dashboardRepo.findById(id)
                .map(e -> ApiResponse.success("Dashboard fetched", toDashboardDTO(e)))
                .orElse(ApiResponse.error("Dashboard not found"));
    }

    @Transactional
    public ApiResponse<DashboardShowcaseSectionDTO> createDashboard(DashboardShowcaseSectionDTO dto) {
        DashboardShowcaseSection entity = DashboardShowcaseSection.builder()
                .badge(dto.getBadge()).heading(dto.getHeading())
                .subheading(dto.getSubheading()).ctaText(dto.getCtaText())
                .tabsJson(dto.getTabsJson()).primaryColor(dto.getPrimaryColor())
                .isActive(dto.getIsActive() != null ? dto.getIsActive() : true)
                .build();
        return ApiResponse.success("Dashboard created", toDashboardDTO(dashboardRepo.save(entity)));
    }

    @Transactional
    public ApiResponse<DashboardShowcaseSectionDTO> updateDashboard(Long id, DashboardShowcaseSectionDTO dto) {
        return dashboardRepo.findById(id).map(entity -> {
            if (dto.getBadge() != null) entity.setBadge(dto.getBadge());
            if (dto.getHeading() != null) entity.setHeading(dto.getHeading());
            if (dto.getSubheading() != null) entity.setSubheading(dto.getSubheading());
            if (dto.getCtaText() != null) entity.setCtaText(dto.getCtaText());
            if (dto.getTabsJson() != null) entity.setTabsJson(dto.getTabsJson());
            if (dto.getPrimaryColor() != null) entity.setPrimaryColor(dto.getPrimaryColor());
            if (dto.getIsActive() != null) entity.setIsActive(dto.getIsActive());
            return ApiResponse.success("Dashboard updated", toDashboardDTO(dashboardRepo.save(entity)));
        }).orElse(ApiResponse.error("Dashboard not found"));
    }

    @Transactional
    public ApiResponse<Void> deleteDashboard(Long id) {
        if (!dashboardRepo.existsById(id)) return ApiResponse.error("Dashboard not found");
        dashboardRepo.deleteById(id);
        return ApiResponse.success("Dashboard deleted", null);
    }

    private DashboardShowcaseSectionDTO toDashboardDTO(DashboardShowcaseSection e) {
        return DashboardShowcaseSectionDTO.builder()
                .id(e.getId()).badge(e.getBadge()).heading(e.getHeading())
                .subheading(e.getSubheading()).ctaText(e.getCtaText())
                .tabsJson(e.getTabsJson()).primaryColor(e.getPrimaryColor())
                .isActive(e.getIsActive()).build();
    }

    // ════════════════════════════════════════════════════════════════
    //  WHY CHOOSE US SECTION
    // ════════════════════════════════════════════════════════════════

    public ApiResponse<WhyChooseUsSectionDTO> getWhyChooseUs() {
        return whyRepo.findAll().stream().findFirst()
                .map(e -> ApiResponse.success("WhyChooseUs fetched", toWhyDTO(e)))
                .orElse(ApiResponse.error("WhyChooseUs not found"));
    }

    public ApiResponse<WhyChooseUsSectionDTO> getWhyChooseUsById(Long id) {
        return whyRepo.findById(id)
                .map(e -> ApiResponse.success("WhyChooseUs fetched", toWhyDTO(e)))
                .orElse(ApiResponse.error("WhyChooseUs not found"));
    }

    @Transactional
    public ApiResponse<WhyChooseUsSectionDTO> createWhyChooseUs(WhyChooseUsSectionDTO dto) {
        WhyChooseUsSection entity = WhyChooseUsSection.builder()
                .badge(dto.getBadge()).heading(dto.getHeading())
                .headingHighlight(dto.getHeadingHighlight()).subheading(dto.getSubheading())
                .featuresJson(dto.getFeaturesJson()).primaryColor(dto.getPrimaryColor())
                .isActive(dto.getIsActive() != null ? dto.getIsActive() : true)
                .build();
        return ApiResponse.success("WhyChooseUs created", toWhyDTO(whyRepo.save(entity)));
    }

    @Transactional
    public ApiResponse<WhyChooseUsSectionDTO> updateWhyChooseUs(Long id, WhyChooseUsSectionDTO dto) {
        return whyRepo.findById(id).map(entity -> {
            if (dto.getBadge() != null) entity.setBadge(dto.getBadge());
            if (dto.getHeading() != null) entity.setHeading(dto.getHeading());
            if (dto.getHeadingHighlight() != null) entity.setHeadingHighlight(dto.getHeadingHighlight());
            if (dto.getSubheading() != null) entity.setSubheading(dto.getSubheading());
            if (dto.getFeaturesJson() != null) entity.setFeaturesJson(dto.getFeaturesJson());
            if (dto.getPrimaryColor() != null) entity.setPrimaryColor(dto.getPrimaryColor());
            if (dto.getIsActive() != null) entity.setIsActive(dto.getIsActive());
            return ApiResponse.success("WhyChooseUs updated", toWhyDTO(whyRepo.save(entity)));
        }).orElse(ApiResponse.error("WhyChooseUs not found"));
    }

    @Transactional
    public ApiResponse<Void> deleteWhyChooseUs(Long id) {
        if (!whyRepo.existsById(id)) return ApiResponse.error("WhyChooseUs not found");
        whyRepo.deleteById(id);
        return ApiResponse.success("WhyChooseUs deleted", null);
    }

    private WhyChooseUsSectionDTO toWhyDTO(WhyChooseUsSection e) {
        return WhyChooseUsSectionDTO.builder()
                .id(e.getId()).badge(e.getBadge()).heading(e.getHeading())
                .headingHighlight(e.getHeadingHighlight()).subheading(e.getSubheading())
                .featuresJson(e.getFeaturesJson()).primaryColor(e.getPrimaryColor())
                .isActive(e.getIsActive()).build();
    }

    // ════════════════════════════════════════════════════════════════
    //  INDUSTRIES SECTION
    // ════════════════════════════════════════════════════════════════

    public ApiResponse<IndustriesSectionDTO> getIndustries() {
        return industriesRepo.findAll().stream().findFirst()
                .map(e -> ApiResponse.success("Industries fetched", toIndustriesDTO(e)))
                .orElse(ApiResponse.error("Industries not found"));
    }

    public ApiResponse<IndustriesSectionDTO> getIndustriesById(Long id) {
        return industriesRepo.findById(id)
                .map(e -> ApiResponse.success("Industries fetched", toIndustriesDTO(e)))
                .orElse(ApiResponse.error("Industries not found"));
    }

    @Transactional
    public ApiResponse<IndustriesSectionDTO> createIndustries(IndustriesSectionDTO dto) {
        IndustriesSection entity = IndustriesSection.builder()
                .badge(dto.getBadge()).heading(dto.getHeading())
                .headingHighlight(dto.getHeadingHighlight()).subheading(dto.getSubheading())
                .industriesJson(dto.getIndustriesJson()).ctaCardTitle(dto.getCtaCardTitle())
                .ctaCardDesc(dto.getCtaCardDesc()).primaryColor(dto.getPrimaryColor())
                .isActive(dto.getIsActive() != null ? dto.getIsActive() : true)
                .build();
        return ApiResponse.success("Industries created", toIndustriesDTO(industriesRepo.save(entity)));
    }

    @Transactional
    public ApiResponse<IndustriesSectionDTO> updateIndustries(Long id, IndustriesSectionDTO dto) {
        return industriesRepo.findById(id).map(entity -> {
            if (dto.getBadge() != null) entity.setBadge(dto.getBadge());
            if (dto.getHeading() != null) entity.setHeading(dto.getHeading());
            if (dto.getHeadingHighlight() != null) entity.setHeadingHighlight(dto.getHeadingHighlight());
            if (dto.getSubheading() != null) entity.setSubheading(dto.getSubheading());
            if (dto.getIndustriesJson() != null) entity.setIndustriesJson(dto.getIndustriesJson());
            if (dto.getCtaCardTitle() != null) entity.setCtaCardTitle(dto.getCtaCardTitle());
            if (dto.getCtaCardDesc() != null) entity.setCtaCardDesc(dto.getCtaCardDesc());
            if (dto.getPrimaryColor() != null) entity.setPrimaryColor(dto.getPrimaryColor());
            if (dto.getIsActive() != null) entity.setIsActive(dto.getIsActive());
            return ApiResponse.success("Industries updated", toIndustriesDTO(industriesRepo.save(entity)));
        }).orElse(ApiResponse.error("Industries not found"));
    }

    @Transactional
    public ApiResponse<Void> deleteIndustries(Long id) {
        if (!industriesRepo.existsById(id)) return ApiResponse.error("Industries not found");
        industriesRepo.deleteById(id);
        return ApiResponse.success("Industries deleted", null);
    }

    private IndustriesSectionDTO toIndustriesDTO(IndustriesSection e) {
        return IndustriesSectionDTO.builder()
                .id(e.getId()).badge(e.getBadge()).heading(e.getHeading())
                .headingHighlight(e.getHeadingHighlight()).subheading(e.getSubheading())
                .industriesJson(e.getIndustriesJson()).ctaCardTitle(e.getCtaCardTitle())
                .ctaCardDesc(e.getCtaCardDesc()).primaryColor(e.getPrimaryColor())
                .isActive(e.getIsActive()).build();
    }

    // ════════════════════════════════════════════════════════════════
    //  BENEFITS SECTION
    // ════════════════════════════════════════════════════════════════

    public ApiResponse<BenefitsSectionDTO> getBenefits() {
        return benefitsRepo.findAll().stream().findFirst()
                .map(e -> ApiResponse.success("Benefits fetched", toBenefitsDTO(e)))
                .orElse(ApiResponse.error("Benefits not found"));
    }

    public ApiResponse<BenefitsSectionDTO> getBenefitsById(Long id) {
        return benefitsRepo.findById(id)
                .map(e -> ApiResponse.success("Benefits fetched", toBenefitsDTO(e)))
                .orElse(ApiResponse.error("Benefits not found"));
    }

    @Transactional
    public ApiResponse<BenefitsSectionDTO> createBenefits(BenefitsSectionDTO dto) {
        BenefitsSection entity = BenefitsSection.builder()
                .badge(dto.getBadge()).heading(dto.getHeading())
                .headingHighlight(dto.getHeadingHighlight())
                .benefitsJson(dto.getBenefitsJson()).primaryColor(dto.getPrimaryColor())
                .isActive(dto.getIsActive() != null ? dto.getIsActive() : true)
                .build();
        return ApiResponse.success("Benefits created", toBenefitsDTO(benefitsRepo.save(entity)));
    }

    @Transactional
    public ApiResponse<BenefitsSectionDTO> updateBenefits(Long id, BenefitsSectionDTO dto) {
        return benefitsRepo.findById(id).map(entity -> {
            if (dto.getBadge() != null) entity.setBadge(dto.getBadge());
            if (dto.getHeading() != null) entity.setHeading(dto.getHeading());
            if (dto.getHeadingHighlight() != null) entity.setHeadingHighlight(dto.getHeadingHighlight());
            if (dto.getBenefitsJson() != null) entity.setBenefitsJson(dto.getBenefitsJson());
            if (dto.getPrimaryColor() != null) entity.setPrimaryColor(dto.getPrimaryColor());
            if (dto.getIsActive() != null) entity.setIsActive(dto.getIsActive());
            return ApiResponse.success("Benefits updated", toBenefitsDTO(benefitsRepo.save(entity)));
        }).orElse(ApiResponse.error("Benefits not found"));
    }

    @Transactional
    public ApiResponse<Void> deleteBenefits(Long id) {
        if (!benefitsRepo.existsById(id)) return ApiResponse.error("Benefits not found");
        benefitsRepo.deleteById(id);
        return ApiResponse.success("Benefits deleted", null);
    }

    private BenefitsSectionDTO toBenefitsDTO(BenefitsSection e) {
        return BenefitsSectionDTO.builder()
                .id(e.getId()).badge(e.getBadge()).heading(e.getHeading())
                .headingHighlight(e.getHeadingHighlight()).benefitsJson(e.getBenefitsJson())
                .primaryColor(e.getPrimaryColor()).isActive(e.getIsActive()).build();
    }

    // ════════════════════════════════════════════════════════════════
    //  TESTIMONIALS SECTION
    // ════════════════════════════════════════════════════════════════

    public ApiResponse<TestimonialsSectionDTO> getTestimonials() {
        return testimonialsRepo.findAll().stream().findFirst()
                .map(e -> ApiResponse.success("Testimonials fetched", toTestimonialsDTO(e)))
                .orElse(ApiResponse.error("Testimonials not found"));
    }

    public ApiResponse<TestimonialsSectionDTO> getTestimonialsById(Long id) {
        return testimonialsRepo.findById(id)
                .map(e -> ApiResponse.success("Testimonials fetched", toTestimonialsDTO(e)))
                .orElse(ApiResponse.error("Testimonials not found"));
    }

    @Transactional
    public ApiResponse<TestimonialsSectionDTO> createTestimonials(TestimonialsSectionDTO dto) {
        TestimonialsSection entity = TestimonialsSection.builder()
                .badge(dto.getBadge()).heading(dto.getHeading())
                .headingHighlight(dto.getHeadingHighlight())
                .testimonialsJson(dto.getTestimonialsJson()).primaryColor(dto.getPrimaryColor())
                .isActive(dto.getIsActive() != null ? dto.getIsActive() : true)
                .build();
        return ApiResponse.success("Testimonials created", toTestimonialsDTO(testimonialsRepo.save(entity)));
    }

    @Transactional
    public ApiResponse<TestimonialsSectionDTO> updateTestimonials(Long id, TestimonialsSectionDTO dto) {
        return testimonialsRepo.findById(id).map(entity -> {
            if (dto.getBadge() != null) entity.setBadge(dto.getBadge());
            if (dto.getHeading() != null) entity.setHeading(dto.getHeading());
            if (dto.getHeadingHighlight() != null) entity.setHeadingHighlight(dto.getHeadingHighlight());
            if (dto.getTestimonialsJson() != null) entity.setTestimonialsJson(dto.getTestimonialsJson());
            if (dto.getPrimaryColor() != null) entity.setPrimaryColor(dto.getPrimaryColor());
            if (dto.getIsActive() != null) entity.setIsActive(dto.getIsActive());
            return ApiResponse.success("Testimonials updated", toTestimonialsDTO(testimonialsRepo.save(entity)));
        }).orElse(ApiResponse.error("Testimonials not found"));
    }

    @Transactional
    public ApiResponse<Void> deleteTestimonials(Long id) {
        if (!testimonialsRepo.existsById(id)) return ApiResponse.error("Testimonials not found");
        testimonialsRepo.deleteById(id);
        return ApiResponse.success("Testimonials deleted", null);
    }

    private TestimonialsSectionDTO toTestimonialsDTO(TestimonialsSection e) {
        return TestimonialsSectionDTO.builder()
                .id(e.getId()).badge(e.getBadge()).heading(e.getHeading())
                .headingHighlight(e.getHeadingHighlight()).testimonialsJson(e.getTestimonialsJson())
                .primaryColor(e.getPrimaryColor()).isActive(e.getIsActive()).build();
    }

    // ════════════════════════════════════════════════════════════════
    //  PRICING SECTION
    // ════════════════════════════════════════════════════════════════

    public ApiResponse<PricingSectionDTO> getPricing() {
        return pricingRepo.findAll().stream().findFirst()
                .map(e -> ApiResponse.success("Pricing fetched", toPricingDTO(e)))
                .orElse(ApiResponse.error("Pricing not found"));
    }

    public ApiResponse<PricingSectionDTO> getPricingById(Long id) {
        return pricingRepo.findById(id)
                .map(e -> ApiResponse.success("Pricing fetched", toPricingDTO(e)))
                .orElse(ApiResponse.error("Pricing not found"));
    }

    @Transactional
    public ApiResponse<PricingSectionDTO> createPricing(PricingSectionDTO dto) {
        PricingSection entity = PricingSection.builder()
                .badge(dto.getBadge()).heading(dto.getHeading())
                .headingHighlight(dto.getHeadingHighlight()).subheading(dto.getSubheading())
                .yearlySaveText(dto.getYearlySaveText()).footerNote(dto.getFooterNote())
                .plansJson(dto.getPlansJson()).trustBadgesJson(dto.getTrustBadgesJson())
                .primaryColor(dto.getPrimaryColor())
                .isActive(dto.getIsActive() != null ? dto.getIsActive() : true)
                .build();
        return ApiResponse.success("Pricing created", toPricingDTO(pricingRepo.save(entity)));
    }

    @Transactional
    public ApiResponse<PricingSectionDTO> updatePricing(Long id, PricingSectionDTO dto) {
        return pricingRepo.findById(id).map(entity -> {
            if (dto.getBadge() != null) entity.setBadge(dto.getBadge());
            if (dto.getHeading() != null) entity.setHeading(dto.getHeading());
            if (dto.getHeadingHighlight() != null) entity.setHeadingHighlight(dto.getHeadingHighlight());
            if (dto.getSubheading() != null) entity.setSubheading(dto.getSubheading());
            if (dto.getYearlySaveText() != null) entity.setYearlySaveText(dto.getYearlySaveText());
            if (dto.getFooterNote() != null) entity.setFooterNote(dto.getFooterNote());
            if (dto.getPlansJson() != null) entity.setPlansJson(dto.getPlansJson());
            if (dto.getTrustBadgesJson() != null) entity.setTrustBadgesJson(dto.getTrustBadgesJson());
            if (dto.getPrimaryColor() != null) entity.setPrimaryColor(dto.getPrimaryColor());
            if (dto.getIsActive() != null) entity.setIsActive(dto.getIsActive());
            return ApiResponse.success("Pricing updated", toPricingDTO(pricingRepo.save(entity)));
        }).orElse(ApiResponse.error("Pricing not found"));
    }

    @Transactional
    public ApiResponse<Void> deletePricing(Long id) {
        if (!pricingRepo.existsById(id)) return ApiResponse.error("Pricing not found");
        pricingRepo.deleteById(id);
        return ApiResponse.success("Pricing deleted", null);
    }

    private PricingSectionDTO toPricingDTO(PricingSection e) {
        return PricingSectionDTO.builder()
                .id(e.getId()).badge(e.getBadge()).heading(e.getHeading())
                .headingHighlight(e.getHeadingHighlight()).subheading(e.getSubheading())
                .yearlySaveText(e.getYearlySaveText()).footerNote(e.getFooterNote())
                .plansJson(e.getPlansJson()).trustBadgesJson(e.getTrustBadgesJson())
                .primaryColor(e.getPrimaryColor()).isActive(e.getIsActive()).build();
    }

    // ════════════════════════════════════════════════════════════════
    //  FINAL CTA SECTION
    // ════════════════════════════════════════════════════════════════

    public ApiResponse<FinalCtaSectionDTO> getFinalCta() {
        return finalCtaRepo.findAll().stream().findFirst()
                .map(e -> ApiResponse.success("FinalCta fetched", toFinalCtaDTO(e)))
                .orElse(ApiResponse.error("FinalCta not found"));
    }

    public ApiResponse<FinalCtaSectionDTO> getFinalCtaById(Long id) {
        return finalCtaRepo.findById(id)
                .map(e -> ApiResponse.success("FinalCta fetched", toFinalCtaDTO(e)))
                .orElse(ApiResponse.error("FinalCta not found"));
    }

    @Transactional
    public ApiResponse<FinalCtaSectionDTO> createFinalCta(FinalCtaSectionDTO dto) {
        FinalCtaSection entity = FinalCtaSection.builder()
                .badge(dto.getBadge()).heading(dto.getHeading())
                .headingHighlight(dto.getHeadingHighlight()).subheading(dto.getSubheading())
                .ctaPrimaryText(dto.getCtaPrimaryText()).ctaSecondaryText(dto.getCtaSecondaryText())
                .statsJson(dto.getStatsJson()).trustItemsJson(dto.getTrustItemsJson())
                .floatingCardsJson(dto.getFloatingCardsJson()).primaryColor(dto.getPrimaryColor())
                .isActive(dto.getIsActive() != null ? dto.getIsActive() : true)
                .build();
        return ApiResponse.success("FinalCta created", toFinalCtaDTO(finalCtaRepo.save(entity)));
    }

    @Transactional
    public ApiResponse<FinalCtaSectionDTO> updateFinalCta(Long id, FinalCtaSectionDTO dto) {
        return finalCtaRepo.findById(id).map(entity -> {
            if (dto.getBadge() != null) entity.setBadge(dto.getBadge());
            if (dto.getHeading() != null) entity.setHeading(dto.getHeading());
            if (dto.getHeadingHighlight() != null) entity.setHeadingHighlight(dto.getHeadingHighlight());
            if (dto.getSubheading() != null) entity.setSubheading(dto.getSubheading());
            if (dto.getCtaPrimaryText() != null) entity.setCtaPrimaryText(dto.getCtaPrimaryText());
            if (dto.getCtaSecondaryText() != null) entity.setCtaSecondaryText(dto.getCtaSecondaryText());
            if (dto.getStatsJson() != null) entity.setStatsJson(dto.getStatsJson());
            if (dto.getTrustItemsJson() != null) entity.setTrustItemsJson(dto.getTrustItemsJson());
            if (dto.getFloatingCardsJson() != null) entity.setFloatingCardsJson(dto.getFloatingCardsJson());
            if (dto.getPrimaryColor() != null) entity.setPrimaryColor(dto.getPrimaryColor());
            if (dto.getIsActive() != null) entity.setIsActive(dto.getIsActive());
            return ApiResponse.success("FinalCta updated", toFinalCtaDTO(finalCtaRepo.save(entity)));
        }).orElse(ApiResponse.error("FinalCta not found"));
    }

    @Transactional
    public ApiResponse<Void> deleteFinalCta(Long id) {
        if (!finalCtaRepo.existsById(id)) return ApiResponse.error("FinalCta not found");
        finalCtaRepo.deleteById(id);
        return ApiResponse.success("FinalCta deleted", null);
    }

    private FinalCtaSectionDTO toFinalCtaDTO(FinalCtaSection e) {
        return FinalCtaSectionDTO.builder()
                .id(e.getId()).badge(e.getBadge()).heading(e.getHeading())
                .headingHighlight(e.getHeadingHighlight()).subheading(e.getSubheading())
                .ctaPrimaryText(e.getCtaPrimaryText()).ctaSecondaryText(e.getCtaSecondaryText())
                .statsJson(e.getStatsJson()).trustItemsJson(e.getTrustItemsJson())
                .floatingCardsJson(e.getFloatingCardsJson()).primaryColor(e.getPrimaryColor())
                .isActive(e.getIsActive()).build();
    }
}