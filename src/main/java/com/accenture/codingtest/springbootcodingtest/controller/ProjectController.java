package com.accenture.codingtest.springbootcodingtest.controller;

import com.accenture.codingtest.springbootcodingtest.model.ProjectDto;
import com.accenture.codingtest.springbootcodingtest.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public Collection<ProjectDto> findAll() {
        return projectService.findAll();
    }

    @GetMapping("/{id}")
    public ProjectDto findById(@PathVariable("id") String id) {
        return projectService.findById(UUID.fromString(id));
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ROLE_PRODUCT_OWNER')")
    public ProjectDto create(@RequestBody ProjectDto projectDto) {
        return projectService.create(projectDto);
    }

    @PutMapping("/{id}")
    public ProjectDto update(@PathVariable("id") String id, @RequestBody ProjectDto projectDto) {
        return projectService.update(UUID.fromString(id), projectDto);
    }

    @PatchMapping("/{id}")
    public ProjectDto updatePartially(@PathVariable("id") String id, @RequestBody ProjectDto projectDto) {
        return projectService.updatePartially(UUID.fromString(id), projectDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") String id) {
        projectService.deleteById(UUID.fromString(id));
        return ResponseEntity.ok().build();
    }


}
