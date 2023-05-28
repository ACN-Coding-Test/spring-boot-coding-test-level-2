package com.accenture.codingtest.springbootcodingtest.service;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service("projectService")
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    public Project findById(UUID projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with Id: " + projectId));
    }

    public Page<Project> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }


    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(UUID projectId, Project project) {
        Project fetch = findById(projectId);
        fetch.setName(project.getName());
        return projectRepository.save(fetch);
    }

    public void delete(UUID projectId) {
        Project fetch = findById(projectId);
        projectRepository.delete(fetch);
    }



}
