package com.example.ez.ClientLogos.service;

import com.example.ez.ClientLogos.dto.ClientLogoDTO;

import java.util.List;

public interface ClientLogoService {

    // Public
    List<ClientLogoDTO> getActiveLogos();

    // Admin
    List<ClientLogoDTO> getAllLogos();
    ClientLogoDTO        createLogo(ClientLogoDTO dto);
    ClientLogoDTO        updateLogo(Long id, ClientLogoDTO dto);
    void                 deleteLogo(Long id);
    ClientLogoDTO getLogoById(Long id);
}