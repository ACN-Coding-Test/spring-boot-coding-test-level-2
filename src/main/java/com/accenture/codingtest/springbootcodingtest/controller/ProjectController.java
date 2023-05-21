package com.accenture.codingtest.springbootcodingtest.controller;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{project_id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("project_id") UUID projectId) {
        Project project = fetchProject(projectId);
        return ResponseEntity.ok(project);
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project){
        Project savedProject = projectRepository.save(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);
    }

    @PutMapping("/{project_id}")
    public ResponseEntity<Project> updateProjectById(@PathVariable("project_id") UUID projectId,
                                                     @RequestBody Project project){
        Project foundProject = fetchProject(projectId);

        foundProject.setName(project.getName());

        return ResponseEntity.ok(foundProject);
    }

    @DeleteMapping("/{project_id}")
    public ResponseEntity<Project> deleteProject(@PathVariable("project_id") UUID projectId){
        Project foundProject = fetchProject(projectId);
        projectRepository.delete(foundProject);
        return ResponseEntity.noContent().build();
    }


}
