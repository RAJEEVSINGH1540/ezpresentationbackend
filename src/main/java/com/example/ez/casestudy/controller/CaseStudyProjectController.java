package com.example.ez.casestudy.controller;

import com.example.ez.casestudy.dto.ProjectDTO;
import com.example.ez.casestudy.service.CaseStudyProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")

@RequiredArgsConstructor
public class CaseStudyProjectController {
	private final CaseStudyProjectService caseStudyProjectService;

@PostMapping("/saveCase")
public ResponseEntity<?> saveCaseStudy(@RequestBody ProjectDTO request){
	System.out.println("\n\n Request data is "+request+"\n\n");
	String response = caseStudyProjectService.saveCaseStudy(request);
	return  new ResponseEntity<>(response, HttpStatus.OK);
}
@GetMapping("/getCaseStudy/{id}")
public ResponseEntity<?> getCaseStudy(@PathVariable Long id){
	ProjectDTO dto = caseStudyProjectService.getCaseStudyById(id);
	return new ResponseEntity<>(dto,HttpStatus.OK);
}
@DeleteMapping("/{id}")
public ResponseEntity<?> deleteCaseStudy(@PathVariable Long id){
	String response = caseStudyProjectService.deleteCaseStudy(id);
	return new ResponseEntity<>(response,HttpStatus.OK);
}
@PutMapping("/updateCaseStudy/{id}")
public ResponseEntity<?> updateCaseStudy(@RequestBody ProjectDTO request , @PathVariable Long id){
	String response = caseStudyProjectService.updateCaseStudy(request,id);
	return new ResponseEntity<>(response,HttpStatus.OK);
}
@GetMapping("/getAllProjectSummary")
public ResponseEntity<?> getAllProjectSummary(){
	return new ResponseEntity<>(caseStudyProjectService.getAllProjectSummary(),HttpStatus.OK);
}
@GetMapping("/getAllProject")
public ResponseEntity<?> getAllProject(){
	return new ResponseEntity<>(caseStudyProjectService.getAllCaseStudies(),HttpStatus.OK);
}
}
