package com.accenture.codingtest.springbootcodingtest.controller;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }



    @GetMapping
    //@PreAuthorize("hasRole('ADMIN') or hasRole('PRODUCT_OWNER')")
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.findAll();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{project_id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("project_id") UUID projectId) {
        Project project = projectService.findById(projectId);
        return ResponseEntity.ok(project);
    }

    @PostMapping
    //@PreAuthorize("hasRole('PRODUCT_OWNER')")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project savedProject = projectService.save(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);
    }

    @PutMapping("/{project_id}")
    public ResponseEntity<Project> updateProjectById(@PathVariable("project_id") UUID projectId,
                                                     @RequestBody Project project) {
        Project updated = projectService.updateProject(projectId,project);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{project_id}")
    public ResponseEntity<Project> deleteProject(@PathVariable("project_id") UUID projectId) {
        projectService.delete(projectId);
        return ResponseEntity.noContent().build();
    }


}
