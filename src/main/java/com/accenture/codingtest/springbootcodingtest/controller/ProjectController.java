package com.accenture.codingtest.springbootcodingtest.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.accenture.codingtest.springbootcodingtest.model.Project;
import com.accenture.codingtest.springbootcodingtest.service.ProjectService;


@Controller("/api/v1/projects")
public class ProjectController {
	@Autowired
	private ProjectService projectService;

	public ProjectController(ProjectService projectService) {
		super();
		this.projectService = projectService;
	}
	
//	GET retrieve all resources (ex. GET /api/v1/projects)
	@GetMapping
	public List<Project> retrieveAllProjects() {
		return projectService.findAll();
	}
	

//	GET retrieve one resource by id (ex. GET /api/v1/projects/{project_id})
	@GetMapping("/{project_id}")
	public Optional<Project> getProjectById(@PathVariable("project_id") UUID id) {
		return projectService.findById(id);
	}
//	POST create one resource (ex. POST /api/v1/projects)
	
	@PostMapping
	private void createProject(@RequestBody Project project) {
		projectService.createProject(project);
	}
//	PUT update one resource idempotent (ex. PUT /api/v1/projects/{project_id})
	@PutMapping("/{project_id}")
	private void updateProject(@RequestBody Project project, @PathVariable("project_id") UUID id) {
		projectService.updateProject(project, id);
	}
	
	
//	PATCH update one resource (ex. PATCH /api/v1/projects/{project_id})
	@PatchMapping("/{project_id}")
	private void patchProject(@RequestBody Project project, @PathVariable("project_id") UUID id) {
		projectService.patchProject(project, id);
	}
//	DELETE remove one resource (ex. DELETE /api/v1/projects/{project_id})
	@DeleteMapping("/{project_id}")
	private void deleteProject(@PathVariable("project_id") UUID id) {
		projectService.deleteProject(id);
	}

}
