package com.example.ez.homepage.trusted.repository;

import com.example.ez.homepage.trusted.entity.TrustedBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrustedBrandRepository extends JpaRepository<TrustedBrand, Long> {
    List<TrustedBrand> findAllByOrderBySortOrderAsc();
    List<TrustedBrand> findByActiveTrueOrderBySortOrderAsc();
}