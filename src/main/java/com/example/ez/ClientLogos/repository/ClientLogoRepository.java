package com.example.ez.ClientLogos.repository;

import com.example.ez.ClientLogos.entity.ClientLogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientLogoRepository extends JpaRepository<ClientLogo, Long> {

    List<ClientLogo> findByActiveTrueOrderByDisplayOrderAsc();

    List<ClientLogo> findAllByOrderByDisplayOrderAsc();

    boolean existsByCompanyNameIgnoreCase(String companyName);
}