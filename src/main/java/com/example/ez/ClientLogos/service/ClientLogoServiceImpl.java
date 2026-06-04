package com.example.ez.ClientLogos.service;

import com.example.ez.ClientLogos.dto.ClientLogoDTO;
import com.example.ez.ClientLogos.entity.ClientLogo;
import com.example.ez.ClientLogos.repository.ClientLogoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ClientLogoServiceImpl implements ClientLogoService {

    private final ClientLogoRepository repository;
    private final ClientLogoMapper     mapper;

    @Override
    @Transactional(readOnly = true)
    public List<ClientLogoDTO> getActiveLogos() {
        return repository.findByActiveTrueOrderByDisplayOrderAsc()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientLogoDTO> getAllLogos() {
        return repository.findAllByOrderByDisplayOrderAsc()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ClientLogoDTO getLogoById(Long id) {
        return mapper.toDTO(findOrThrow(id));
    }

    @Override
    public ClientLogoDTO createLogo(ClientLogoDTO dto) {
        log.info("Creating client logo: {}", dto.getCompanyName());
        ClientLogo saved = repository.save(mapper.toEntity(dto));
        return mapper.toDTO(saved);
    }

    @Override
    public ClientLogoDTO updateLogo(Long id, ClientLogoDTO dto) {
        log.info("Updating client logo id={}", id);
        ClientLogo entity = findOrThrow(id);
        mapper.updateEntity(entity, dto);
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public void deleteLogo(Long id) {
        log.info("Deleting client logo id={}", id);
        if (!repository.existsById(id))
            throw new EntityNotFoundException("ClientLogo not found with id: " + id);
        repository.deleteById(id);
    }

    // ── Private helpers ──────────────────────────────────────────
    private ClientLogo findOrThrow(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("ClientLogo not found with id: " + id));
    }
}