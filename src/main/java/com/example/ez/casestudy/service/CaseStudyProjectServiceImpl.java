package com.example.ez.casestudy.service;

import com.example.ez.casestudy.dto.ProjectDTO;
import com.example.ez.casestudy.dto.ProjectSummaryDTO;
import com.example.ez.casestudy.entity.Process;
import com.example.ez.casestudy.entity.*;
import com.example.ez.casestudy.repo.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CaseStudyProjectServiceImpl implements CaseStudyProjectService {

private final ProjectRepository projectRepository;
private final SimpMessagingTemplate messagingTemplate;

// ==================================================================================
// 1. PUBLIC METHODS (Override from Interface)
// ==================================================================================

@Override
public String saveCaseStudy(ProjectDTO request) {
	try {
		// This single method handles both Create (id=null) and Update (id!=null)
		Project saved = saveOrUpdate(request,null);
		String message = "Project '" + saved.getMeta().getTitle() + "' was successfully created" ;
		messagingTemplate.convertAndSend("/topic/notifications", message);
//		String action = (request.getId() != null) ? "updated" : "created";
		return "Case Study "  + " successfully with ID: " + saved.getId();
	} catch (Exception e) {
		log.error("Failed to save Case Study", e);
		throw new RuntimeException("Error saving case study: " + e.getMessage());
	}
}

@Override
public ProjectDTO getCaseStudyById(Long id) {
	log.debug("Fetching Case Study by ID: {}", id);
	Project project = projectRepository.findById(id)
			                  .orElseThrow(() -> new RuntimeException("Case Study not found with ID: " + id));
	return toDto(project);
}

@Override
public List<ProjectDTO> getAllCaseStudies() {
	log.debug("Fetching all Case Studies");
	return projectRepository.findAll().stream()
			       .map(this::toDto)
			       .collect(Collectors.toList());
}

@Override
public String deleteCaseStudy(Long id) {
	if (!projectRepository.existsById(id)) {
		return "Case Study not found with ID: " + id;
	}
	projectRepository.deleteById(id);
	log.info("Deleted Case Study ID: {}", id);
	return "Case Study deleted successfully";
}

@Override
public String updateCaseStudy(ProjectDTO request, Long id) {
	Project project =saveOrUpdate(request,id);
	return "Project with id "+project.getId()+" updated successfully";
}

@Override
public List<ProjectSummaryDTO> getAllProjectSummary() {
	List<Project> list = projectRepository.findAll();
	return toSummaryDtoList(list);
}

// ==================================================================================
// 2. CORE LOGIC: Unified Save or Update
// ==================================================================================

private Project saveOrUpdate(ProjectDTO request,Long id) {
	Long existingId = id;
	boolean isUpdate = existingId != null;
	
	log.info("Starting {} Case Study...", isUpdate ? "UPDATE" : "CREATE");
	
	// A. Fetch Existing or Create New
	Project project;
	if (isUpdate) {
		project = projectRepository.findById(existingId)
				          .orElseThrow(() -> new RuntimeException("Cannot update. Case Study not found: " + existingId));
	} else {
		project = new Project();
	}
	
	// B. Map Simple Fields
	// Exclude complex fields to handle them manually
	BeanUtils.copyProperties(request, project,
			"id", "meta", "results", "challenge", "solution",
			"techStack", "process", "features", "testimonial");
	
	// C. Map Nested Objects
	mapMeta(request.getMeta(), project);
	mapTestimonial(request.getTestimonial(), project);
	
	// D. Map Lists (Clear & Add for Orphan Removal)
	mapResults(request.getResults(), project);
	mapProcess(request.getProcess(), project);
	mapFeatures(request.getFeatures(), project);
	
	// E. Map Complex Nested Lists
	mapTechStack(request.getTechStack(), project);
	
	// F. Map Narratives
	mapNarratives(request, project);
	
	// G. Save
	return projectRepository.save(project);
}

// ==================================================================================
// 3. PRIVATE MAPPING HELPERS
// ==================================================================================

// --- Entity -> DTO ---
private ProjectDTO toDto(Project entity) {
	if (entity == null) return null;
	ProjectDTO dto = new ProjectDTO();

	BeanUtils.copyProperties(entity, dto);

	// Meta
	if (entity.getMeta() != null) {
		ProjectDTO.MetaDTO m = new ProjectDTO.MetaDTO();
		BeanUtils.copyProperties(entity.getMeta(), m);
		dto.setMeta(m);
	}

	// Testimonial
	if (entity.getTestimonial() != null) {
		ProjectDTO.TestimonialDTO t = new ProjectDTO.TestimonialDTO();
		BeanUtils.copyProperties(entity.getTestimonial(), t);
		dto.setTestimonial(t);
	}

	// Narratives
	if(entity.getChallenge() != null) {
		ProjectDTO.NarrativeDTO n = new ProjectDTO.NarrativeDTO();
		n.setDescription(entity.getChallenge().getDescription());
		if(entity.getChallenge().getPoints() != null) n.setPoints(new ArrayList<>(entity.getChallenge().getPoints()));
		dto.setChallenge(n);
	}
	if(entity.getSolution() != null) {
		ProjectDTO.NarrativeDTO n = new ProjectDTO.NarrativeDTO();
		n.setDescription(entity.getSolution().getDescription());
		if(entity.getSolution().getPoints() != null) n.setPoints(new ArrayList<>(entity.getSolution().getPoints()));
		dto.setSolution(n);
	}

	// Lists
	dto.setResults(convertList(entity.getResults(), ProjectDTO.ResultDTO.class));
	dto.setProcess(convertList(entity.getProcess(), ProjectDTO.ProcessDTO.class));
	dto.setFeatures(convertList(entity.getFeatures(), ProjectDTO.FeatureDTO.class));

	// TechStack (Nested List)
	if (entity.getTechStack() != null) {
		dto.setTechStack(entity.getTechStack().stream().map(t -> {
			ProjectDTO.TechStackDTO tDto = new ProjectDTO.TechStackDTO();
			BeanUtils.copyProperties(t, tDto);
			tDto.setTools(t.getTools() != null ? new ArrayList<>(t.getTools()) : null);
			return tDto;
		}).collect(Collectors.toList()));
	}

	return dto;
}

// --- DTO -> Entity Helpers ---

private void mapMeta(ProjectDTO.MetaDTO dto, Project entity) {
	if (dto != null) {
		if (entity.getMeta() == null) entity.setMeta(new Meta());
		BeanUtils.copyProperties(dto, entity.getMeta());
	}
}

private void mapTestimonial(ProjectDTO.TestimonialDTO dto, Project entity) {
	if (dto != null) {
		if (entity.getTestimonial() == null) entity.setTestimonial(new Testimonial());
		BeanUtils.copyProperties(dto, entity.getTestimonial());
	}
}

private void mapResults(List<ProjectDTO.ResultDTO> dtos, Project entity) {
	if (dtos != null) {
		entity.getResults().clear();
		entity.getResults().addAll(convertList(dtos, Result.class));
	}
}

private void mapProcess(List<ProjectDTO.ProcessDTO> dtos, Project entity) {
	if (dtos != null) {
		entity.getProcess().clear();
		entity.getProcess().addAll(convertList(dtos, Process.class));
	}
}

private void mapFeatures(List<ProjectDTO.FeatureDTO> dtos, Project entity) {
	if (dtos != null) {
		entity.getFeatures().clear();
		entity.getFeatures().addAll(convertList(dtos, Feature.class));
	}
}

private void mapTechStack(List<ProjectDTO.TechStackDTO> dtos, Project entity) {
	if (dtos != null) {
		entity.getTechStack().clear();
		entity.getTechStack().addAll(dtos.stream().map(d -> {
			TechStack t = new TechStack();
			BeanUtils.copyProperties(d, t);
			t.setTools(new ArrayList<>(d.getTools())); // Deep copy tools
			return t;
		}).collect(Collectors.toList()));
	}
}

private void mapNarratives(ProjectDTO dto, Project entity) {
	// Challenge
	if (dto.getChallenge() != null) {
		Narrative n = new Narrative();
		n.setDescription(dto.getChallenge().getDescription());
		if (dto.getChallenge().getPoints() != null)
			n.setPoints(new ArrayList<>(dto.getChallenge().getPoints()));
		entity.setChallenge(n);
	}
	// Solution
	if (dto.getSolution() != null) {
		Narrative n = new Narrative();
		n.setDescription(dto.getSolution().getDescription());
		if (dto.getSolution().getPoints() != null)
			n.setPoints(new ArrayList<>(dto.getSolution().getPoints()));
		entity.setSolution(n);
	}
}

// Generic Helper
private <S, T> List<T> convertList(List<S> source, Class<T> targetClass) {
	if (source == null) return new ArrayList<>();
	return source.stream().map(s -> {
		try {
			T t = targetClass.getDeclaredConstructor().newInstance();
			BeanUtils.copyProperties(s, t);
			return t;
		} catch (Exception e) {
			log.error("Mapping Error", e);
			return null;
		}
	}).collect(Collectors.toList());
}
public ProjectSummaryDTO toSummaryDto(Project entity) {
	if (entity == null) return null;
	
	ProjectSummaryDTO dto = new ProjectSummaryDTO();
	dto.setId(entity.getId());
	dto.setImage(entity.getHeroImage());
	
	// 1. Flatten Meta
	if (entity.getMeta() != null) {
		dto.setTitle(entity.getMeta().getTitle());
		dto.setClient(entity.getMeta().getClient());
		dto.setCategory(entity.getMeta().getCategory());
		dto.setYear(entity.getMeta().getYear());
	}
	
	// 2. Shorten Description (Take from Solution or Challenge)
	if (entity.getSolution() != null && entity.getSolution().getDescription() != null) {
		String fullDesc = entity.getSolution().getDescription();
		// Truncate to 100 chars to keep the card clean
		dto.setDescription(fullDesc.length() > 100 ? fullDesc.substring(0, 100) + "..." : fullDesc);
	}
	
	// 3. Create Impact String (Combine Value + Metric from the first Result)
	if (entity.getResults() != null && !entity.getResults().isEmpty()) {
		Result firstResult = entity.getResults().get(0);
		// e.g., "+245%" + " " + "Revenue"
		dto.setImpact(firstResult.getValue() + " " + firstResult.getMetric());
	} else {
		dto.setImpact("High Impact"); // Default fallback
	}
	
	// 4. Flatten Tech Stack (Collect all tools into one list)
	if (entity.getTechStack() != null) {
		List<String> allTools = entity.getTechStack().stream()
				                        .flatMap(stack -> stack.getTools().stream()) // Flatten nested lists
				                        .limit(3) // Take only top 3 tags
				                        .collect(Collectors.toList());
		dto.setTags(allTools);
	}
	
	return dto;
}

// Helper for list conversion
public List<ProjectSummaryDTO> toSummaryDtoList(List<Project> entities) {
	if (entities == null) return null;
	return entities.stream().map(this::toSummaryDto).collect(Collectors.toList());
}
}