package com.example.ez.footter.repository;

import com.example.ez.footter.entity.FooterContent;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FooterRepository extends JpaRepository<FooterContent, Long> {

    @Modifying
    @Transactional
    @Query("""
        UPDATE FooterContent f SET
            f.logoUrl            = :logoUrl,
            f.description        = :description,
            f.copyrightText      = :copyrightText,
            f.quickLinksHeading  = :quickLinksHeading,
            f.servicesHeading    = :servicesHeading,
            f.informationHeading = :informationHeading,
            f.socialLinksJson    = :socialLinksJson,
            f.quickLinksJson     = :quickLinksJson,
            f.serviceLinksJson   = :serviceLinksJson,
            f.infoLinksJson      = :infoLinksJson
        WHERE f.id = :id
    """)
    int updateById(
            @Param("id")                 Long   id,
            @Param("logoUrl")            String logoUrl,
            @Param("description")        String description,
            @Param("copyrightText")      String copyrightText,
            @Param("quickLinksHeading")  String quickLinksHeading,
            @Param("servicesHeading")    String servicesHeading,
            @Param("informationHeading") String informationHeading,
            @Param("socialLinksJson")    String socialLinksJson,
            @Param("quickLinksJson")     String quickLinksJson,
            @Param("serviceLinksJson")   String serviceLinksJson,
            @Param("infoLinksJson")      String infoLinksJson
    );
}