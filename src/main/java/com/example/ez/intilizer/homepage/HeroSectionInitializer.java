package com.example.ez.intilizer.homepage;

import com.example.ez.homepage.HeroSection.entity.HomeHeroSection;
import com.example.ez.homepage.HeroSection.repository.HomeHeroSectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@RequiredArgsConstructor
public class HeroSectionInitializer implements CommandLineRunner {

    private final HomeHeroSectionRepository repo;

    @Override
    public void run(String... args) {
        if (repo.count() == 0) {
            repo.save(HomeHeroSection.builder()
                    .eyebrowLabel("Enterprise Resource Planning")
                    .headlineLine1("Build, Manage & Scale Your")
                    .headlineLine2("Construction Business with")
                    .headlineHighlight("Smart ERP")
                    .subheadline("EZ Construction delivers purpose-built ERP software that unifies your project management, procurement, workforce, finance, and site operations — all in one powerful platform built for the construction industry.")
                    .stat1Value("500+").stat1Label("Projects Delivered")
                    .stat2Value("98%").stat2Label("Client Satisfaction")
                    .stat3Value("40%").stat3Label("Cost Reduction")
                    .stat4Value("15+").stat4Label("Years of Expertise")
                    .bgImageUrl("/uploads/bg-vertical-bars.avif")
                    .dashboardImageUrl("/uploads/dashboard.avif")
                    .overviewCardImageUrl("/uploads/overview-card.avif")
                    .progressCardImageUrl("/uploads/progress-card.avif")
                    .build());
        }
    }
}