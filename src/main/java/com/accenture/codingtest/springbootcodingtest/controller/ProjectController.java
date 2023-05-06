package com.accenture.codingtest.springbootcodingtest.controller;

import com.accenture.codingtest.springbootcodingtest.model.ProjectDTO;
import com.accenture.codingtest.springbootcodingtest.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    private ResponseEntity<List<ProjectDTO>> getProjectList() {
        if (!projectService.getAllProjects().isEmpty()) {
            return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{project_id}")
    private ResponseEntity<ProjectDTO> getProject(@PathVariable("project_id") UUID projectId) {
        ProjectDTO project = projectService.getProject(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<ProjectDTO> saveProject(@RequestBody @Valid ProjectDTO projectDTO) {
        return new ResponseEntity<>(projectService.createProject(projectDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{project_id}")
    private ResponseEntity<ProjectDTO> updateProjectByPUT(@PathVariable("project_id") UUID projectId, @RequestBody @Valid ProjectDTO projectDTO) {
        return new ResponseEntity<>(projectService.updateProjectByPUT(projectId, projectDTO), HttpStatus.OK);
    }

    @PatchMapping("/{project_id}")
    private ResponseEntity<?> updateProjectByPATCH(@PathVariable("project_id") UUID projectId, @RequestBody ProjectDTO projectDTO) {
        return projectService.updateProjectByPatch(projectId, projectDTO);
    }

    @DeleteMapping("/{project_id}")
    private ResponseEntity<?> deleteProject(@PathVariable("project_id") UUID projectId) {
        projectService.deleteProject(projectId);
        return new ResponseEntity<>("Project deleted successfully!", HttpStatus.OK);
    }
}
