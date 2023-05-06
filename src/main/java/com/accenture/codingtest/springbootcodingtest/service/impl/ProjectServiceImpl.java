package com.accenture.codingtest.springbootcodingtest.service.impl;

import com.accenture.codingtest.springbootcodingtest.model.ProjectDTO;
import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.repository.ProjectRepository;
import com.accenture.codingtest.springbootcodingtest.repository.TaskRepository;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepository;
import com.accenture.codingtest.springbootcodingtest.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(project -> new ProjectDTO(project.getId(), project.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDTO getProject(UUID projectId) {
        return projectRepository.findById(projectId)
                .map(project -> new ProjectDTO(project.getId(), project.getName()))
                .orElseThrow(() -> new NoSuchElementException("Project was not found with ID : " + projectId));
    }

    @Override
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project project = projectRepository.save(new Project(projectDTO.getName()));
        return new ProjectDTO(project.getId(), project.getName());
    }

    @Override
    public ProjectDTO updateProjectByPUT(UUID projectId, ProjectDTO projectDTO) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchElementException("Project was not found with ID : " + projectId));
        project.setName(project.getName());
        Project updatedProject = projectRepository.save(project);
        return new ProjectDTO(updatedProject.getId(), updatedProject.getName());
    }

    @Override
    public ResponseEntity<?> updateProjectByPatch(UUID projectId, ProjectDTO projectDTO) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchElementException("Project was not found with ID : " + projectId));
        if (Objects.nonNull(projectDTO.getName())) {
            if (!projectDTO.getName().trim().isEmpty()) {
                project.setName(projectDTO.getName());
            } else {
                return new ResponseEntity<>("Project name should not be empty", HttpStatus.BAD_REQUEST);
            }
        }

        Project updatedProject = projectRepository.save(project);
        return new ResponseEntity<>(new ProjectDTO(updatedProject.getId(), updatedProject.getName()), HttpStatus.OK);
    }

    @Override
    public void deleteProject(UUID projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new EmptyResultDataAccessException("User was not found with ID : " + projectId, 0);
        }
        projectRepository.deleteById(projectId);
    }
}
