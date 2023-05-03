package com.accenture.codingtest.springbootcodingtest.service;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.model.ProjectDto;
import com.accenture.codingtest.springbootcodingtest.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    public Collection<ProjectDto> findAll() {
        Collection<ProjectDto> all = new ArrayList<>();
        projectRepository.findAll().forEach(obj -> {
            all.add(modelMapper.map(obj, ProjectDto.class));
        });
        return all;
    }

    public ProjectDto findById(UUID id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found. Id: " + id));
        return modelMapper.map(project, ProjectDto.class);
    }

    public ProjectDto create(ProjectDto projectDto) {
        Project entity = modelMapper.map(projectDto, Project.class);
        Project createdEntity = projectRepository.save(entity);
        return modelMapper.map(createdEntity, ProjectDto.class);
    }

    public ProjectDto update(UUID id, ProjectDto projectDto) {
        boolean existsById = projectRepository.existsById(id);
        if (existsById) {
            Project entityProject = modelMapper.map(projectDto, Project.class);
            Project updatedEntity = projectRepository.save(entityProject);
            return modelMapper.map(updatedEntity, ProjectDto.class);
        }
        throw new EntityNotFoundException("Project not found. Id: " + id);
    }

    public ProjectDto updatePartially(UUID id, ProjectDto projectDto) {
        ProjectDto target = findById(id);
        modelMapper.map(projectDto, target);
        Project targetEntity = modelMapper.map(target, Project.class);
        projectRepository.save(targetEntity);
        return target;
    }

    public void deleteById(UUID id) {
        projectRepository.deleteById(id);
    }
}
