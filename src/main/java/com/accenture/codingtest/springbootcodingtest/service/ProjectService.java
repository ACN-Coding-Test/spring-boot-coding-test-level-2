package com.accenture.codingtest.springbootcodingtest.service;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    public Project findById(UUID projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with Id: " + projectId));
        return project;
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project save(Project project) {
        Project saved = projectRepository.save(project);
        return saved;
    }

    public Project updateProject(UUID projectId, Project project) {
        Project fetch = findById(projectId);
        fetch.setName(project.getName());
        return projectRepository.save(fetch);
    }

    public void delete(UUID projectId){
        Project fetch = findById(projectId);
        projectRepository.delete(fetch);
    }


}
