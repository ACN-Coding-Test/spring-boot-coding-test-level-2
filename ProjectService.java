package com.accenture.codingtest.springbootcodingtest.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository repository;

	
	public List<Project> findAllProjects(){
		return repository.findAll();
	}

	
	public Project findOneProject(UUID id) {
		Optional<Project> e = repository.findById(id);
		if (e.isPresent()) {
			return e.get();
		}
		return null;
	}
	
	public void saveProject(Project project) {
		repository.save(project);
	}
	
	public ResponseEntity<Project> updateProject(UUID id,Project project) {
		Project existingProject = repository.findById(id).orElseThrow();
		existingProject.setProject_name(project.getProject_name());
		repository.save(existingProject);
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<Project> updateProjectPartially(UUID id,  Project project) {
		try {
			Project existingProject = repository.findById(id).get();
			existingProject.setProject_name(project.getProject_name());
			return new ResponseEntity<Project>(repository.save(project), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public void deleteProject(UUID id) {
		repository.deleteById(id);
	}

}
