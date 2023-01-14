package com.accenture.codingtest.springbootcodingtest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.entity.exception.RecordNotFoundException;
import com.accenture.codingtest.springbootcodingtest.entity.model.ProjectDto;
import com.accenture.codingtest.springbootcodingtest.service.ProjectService;

@RestController
@RequestMapping(value = "/api/v1/projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@PostMapping("")
	public ProjectDto create(@RequestBody ProjectDto projectDto) {
		return this.convertProjectEntityToProjectDto(
				projectService.save(this.convertProjectDtoToProjectEntity(projectDto)));
	}

	@GetMapping("")
	public List<ProjectDto> findAll() {
		return projectService.findAll().stream().map(this::convertProjectEntityToProjectDto)
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ProjectDto findById(@PathVariable String id) {
		return projectService.findById(id).map(this::convertProjectEntityToProjectDto)
				.orElseThrow(RecordNotFoundException::new);
	}

	private ProjectDto convertProjectEntityToProjectDto(Project project) {
		ProjectDto projectDto = new ProjectDto();
		projectDto.setId(project.getId());
		projectDto.setName(projectDto.getName());

		return projectDto;
	}

	private Project convertProjectDtoToProjectEntity(ProjectDto projectDto) {
		Project project = new Project();
		project.setId(projectDto.getId());
		project.setName(projectDto.getName());

		return project;
	}

}