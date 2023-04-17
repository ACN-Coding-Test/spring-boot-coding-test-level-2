package com.accenture.codingtest.springbootcodingtest.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.codingtest.springbootcodingtest.model.Project;
import com.accenture.codingtest.springbootcodingtest.repository.ProjectRepository;
@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectRepository projectRepository;

	public ProjectServiceImpl(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	@Override
	public List<Project> findAll() {

		return projectRepository.findAll();
	}

	@Override
	public Optional<Project> findById(UUID id) {

		return projectRepository.findById(id);
	}

	@Override
	public void createProject(Project project) {

		projectRepository.save(project);

	}

	@Override
	public void deleteProject(UUID id) {

		Optional<Project> usOptional = projectRepository.findById(id);
		projectRepository.delete(usOptional.get());

	}

	@Override
	public void patchProject(Project project, UUID id) {

		Optional<Project> usOptional = projectRepository.findById(id);
		Project project2 = usOptional.get();
		project2.setName(project.getName());
		projectRepository.save(project2);

	}

	@Override
	public void updateProject(Project project, UUID id) {

		Optional<Project> usOptional = projectRepository.findById(id);
		Project project2 = usOptional.get();
		project2.setName(project.getName());
		projectRepository.save(project2);

	}

}
