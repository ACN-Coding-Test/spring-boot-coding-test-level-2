package com.accenture.codingtest.springbootcodingtest.service;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.entity.Users;
import com.accenture.codingtest.springbootcodingtest.repository.ProjectRepository;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ProjectService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(UserRepository userRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    public Project createNewProject(Project project) {
        projectRepository.save(project);
        return project;
    }

    public List<Project> getAllProjects(UUID userId) {
        Users user = userRepository.findAll().stream().filter(u -> u.getId().equals(userId)).findAny().orElse(null);
        if(Objects.nonNull(user) && user.getId().equals(userId)) {
            return projectRepository.findAll();
        } else {
            return new ArrayList<>();
        }
    }
}
