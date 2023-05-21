package com.accenture.codingtest.springbootcodingtest.controller;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "api/v1/projects")
public class ProjectController {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    private Project fetchProject(UUID projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with Id: " + projectId));
        return project;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('PRODUCT_OWNER')")
    public ResponseEntity<Page<Project>> getAllProjects(
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "0") int pageIndex,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection
    ) {
        Sort.Direction direction = Sort.Direction.ASC;
        if (sortDirection.equalsIgnoreCase("DESC")) {
            direction = Sort.Direction.DESC;
        }
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize, Sort.by(direction, sortBy));
        Page<Project> projects;

        if (q != null) {
            projects = projectRepository.findByNameContainingIgnoreCase(q, pageRequest);
        } else {
            projects = projectRepository.findAll(pageRequest);
        }
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{project_id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("project_id") UUID projectId) {
        Project project = fetchProject(projectId);
        return ResponseEntity.ok(project);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('PRODUCT_OWNER')")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project savedProject = projectRepository.save(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);
    }

    @PutMapping("/{project_id}")
    public ResponseEntity<Project> updateProjectById(@PathVariable("project_id") UUID projectId,
                                                     @RequestBody Project project) {
        Project foundProject = fetchProject(projectId);

        foundProject.setName(project.getName());

        return ResponseEntity.ok(foundProject);
    }

    @DeleteMapping("/{project_id}")
    public ResponseEntity<Project> deleteProject(@PathVariable("project_id") UUID projectId) {
        Project foundProject = fetchProject(projectId);
        projectRepository.delete(foundProject);
        return ResponseEntity.noContent().build();
    }


}
