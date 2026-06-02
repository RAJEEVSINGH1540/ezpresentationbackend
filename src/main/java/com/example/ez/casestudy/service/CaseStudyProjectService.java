package com.example.ez.casestudy.service;

import com.example.ez.casestudy.dto.ProjectDTO;
import com.example.ez.casestudy.dto.ProjectSummaryDTO;

import java.util.List;

public interface CaseStudyProjectService {
String saveCaseStudy(ProjectDTO request);
ProjectDTO getCaseStudyById(Long id);
 List<ProjectDTO> getAllCaseStudies();
 String deleteCaseStudy(Long id);

String updateCaseStudy(ProjectDTO request, Long id);
List<ProjectSummaryDTO> getAllProjectSummary();


}
