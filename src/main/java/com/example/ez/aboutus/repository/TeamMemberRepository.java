package com.example.ez.aboutus.repository;

import com.example.ez.aboutus.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
    List<TeamMember> findByAboutPageIdAndIsActiveTrueOrderBySortOrderAsc(Long aboutPageId);
    List<TeamMember> findByAboutPageIdOrderBySortOrderAsc(Long aboutPageId);
    void deleteByAboutPageId(Long aboutPageId);
}