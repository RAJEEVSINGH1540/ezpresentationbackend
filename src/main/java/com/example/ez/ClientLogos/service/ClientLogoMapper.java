package com.example.ez.ClientLogos.service;

import com.example.ez.ClientLogos.dto.ClientLogoDTO;
import com.example.ez.ClientLogos.entity.ClientLogo;
import org.springframework.stereotype.Component;

@Component
public class ClientLogoMapper {

    public ClientLogoDTO toDTO(ClientLogo entity) {
        if (entity == null) return null;
        return ClientLogoDTO.builder()
                .id(entity.getId())
                .companyName(entity.getCompanyName())
                .logoUrl(entity.getLogoUrl())
                .websiteUrl(entity.getWebsiteUrl())
                .displayOrder(entity.getDisplayOrder())
                .active(entity.getActive())
                .build();
    }

    public ClientLogo toEntity(ClientLogoDTO dto) {
        if (dto == null) return null;
        return ClientLogo.builder()
                .companyName(dto.getCompanyName())
                .logoUrl(dto.getLogoUrl())
                .websiteUrl(dto.getWebsiteUrl())
                .displayOrder(dto.getDisplayOrder() != null ? dto.getDisplayOrder() : 0)
                .active(dto.getActive() != null ? dto.getActive() : true)
                .build();
    }

    public void updateEntity(ClientLogo entity, ClientLogoDTO dto) {
        if (dto.getCompanyName() != null) entity.setCompanyName(dto.getCompanyName());
        if (dto.getLogoUrl()     != null) entity.setLogoUrl(dto.getLogoUrl());
        if (dto.getWebsiteUrl()  != null) entity.setWebsiteUrl(dto.getWebsiteUrl());
        if (dto.getDisplayOrder()!= null) entity.setDisplayOrder(dto.getDisplayOrder());
        if (dto.getActive()      != null) entity.setActive(dto.getActive());
    }
}