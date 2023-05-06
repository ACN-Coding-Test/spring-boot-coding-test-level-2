package com.accenture.codingtest.springbootcodingtest.service;

import com.accenture.codingtest.springbootcodingtest.model.ProjectDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ProjectService {
    List<ProjectDTO> getAllProjects();

    ProjectDTO getProject(UUID projectId);

    ProjectDTO createProject(ProjectDTO projectDTO);

    ProjectDTO updateProjectByPUT(UUID projectId, ProjectDTO projectDTO);

    ResponseEntity<?> updateProjectByPatch(UUID projectId, ProjectDTO projectDTO);

    void deleteProject(UUID projectId);
    ResponseEntity<?> assignTasksToMembers(UUID projectId, UUID userId, List<UUID> tasks);
}
