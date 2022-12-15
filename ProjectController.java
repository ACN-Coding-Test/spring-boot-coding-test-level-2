package com.accenture.codingtest.springbootcodingtest.controller;

import java.util.List;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.service.ProjectService;
import com.accenture.codingtest.springbootcodingtest.AppConstants;
import com.accenture.codingtest.springbootcodingtest.ProjectResponse;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
	
	

	@Autowired
	ProjectService service;
	
	/*@GetMapping
	public List<Project> getAllProjects(){
		return service.findAllProjects();
	}*/
	
	@GetMapping("/{id}")
	public Project findProjectbyId(@PathVariable UUID id){
		return service.findOneProject(id);
	}
	
	@PostMapping
	public void saveProject(@RequestBody Project project) {
		 service.saveProject(project);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Project> updateProject(@PathVariable UUID id,@RequestBody Project project) {
		return service.updateProject(id,project);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Project> updateProjectPartially(@PathVariable UUID id,@RequestBody Project project) {
		return service.updateProjectPartially(id,project);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProject(@PathVariable(value="id")UUID id) {
		service.deleteProject(id);
	}
	
	@GetMapping("/{pageNo}/{pageSize}")
	public List<Project> getPaginated(@PathVariable int pageNo,@PathVariable int pageSize){
		return service.findPaginated(pageNo, pageSize);
	}
	
	@GetMapping("/{pageNo}/{pageSize}/{name}")
	public List<Project> getPaginated(@PathVariable int pageNo,@PathVariable int pageSize, @PathVariable("name") String sort){
		return service.findPaginated(pageNo, pageSize,Sort.by(sort));
	}
	
	 @GetMapping("/paginated")
	    public ProjectResponse getPaginated(
	            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
	            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
	            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
	            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
	    ){
	        return service.getAllProjects(pageNo, pageSize, sortBy, sortDir);
	    }

}
