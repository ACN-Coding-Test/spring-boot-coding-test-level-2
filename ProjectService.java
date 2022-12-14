package com.accenture.codingtest.springbootcodingtest.service;

import java.util.List;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accenture.codingtest.springbootcodingtest.ProjectDto;
import com.accenture.codingtest.springbootcodingtest.ProjectResponse;
import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.repository.ProjectRepository;

@Service
public class ProjectService  implements IProjectService{
	
	@Autowired
	ProjectRepository repository;

	
	/*public List<Project> findAllProjects(){
		return repository.findAll();
	}*/
	
	public Project findOneProject(UUID id) {
		Optional<Project> e = repository.findById(id);
		if (e.isPresent()) {
			return e.get();
		}
		return null;
	}
	
	public void saveProject(Project project) {
		repository.save(project);
	}
	
	public ResponseEntity<Project> updateProject(UUID id,Project project) {
		Project existingProject = repository.findById(id).orElseThrow();
		existingProject.setName(project.getName());
		repository.save(existingProject);
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<Project> updateProjectPartially(UUID id,  Project project) {
		try {
			Project existingProject = repository.findById(id).get();
			existingProject.setName(project.getName());
			return new ResponseEntity<Project>(repository.save(project), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public void deleteProject(UUID id) {
		repository.deleteById(id);
	}

	@Override
	public List<Project> findPaginated(int pageNo, int pageSize,Sort sort) {
		Pageable paging=PageRequest.of(pageNo, pageSize,sort) ;
		Page<Project> pagedResult = repository.findAll(paging);
		return pagedResult.toList();
	}
	
	@Override
	public List<Project> findPaginated(int pageNo, int pageSize) {
		Pageable paging=PageRequest.of(pageNo, pageSize) ;
		Page<Project> pagedResult = repository.findAll(paging);
		return pagedResult.toList();
	}
	
	@Override
	public ProjectDto createProject(ProjectDto projectDto) {
		 // convert DTO to entity
        Project project = mapToEntity(projectDto);
        Project newPost = repository.save(project);

        // convert entity to DTO
        ProjectDto projectResponse = mapToDTO(newPost);
        return projectResponse;
	}
	
	 public ProjectResponse getAllProjects(int pageNo, int pageSize, String sortBy, String sortDir) {

	        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
	                : Sort.by(sortBy).descending();

	        // create Pageable instance
	        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

	        Page<Project> posts = repository.findAll(pageable);

	        // get content for page object
	        List<Project> listOfProjects = posts.getContent();

	        List<ProjectDto> content= listOfProjects.stream().map(project -> mapToDTO(project)).collect(Collectors.toList());

	        ProjectResponse postResponse = new ProjectResponse();
	        postResponse.setContent(content);
	        postResponse.setPageNo(posts.getNumber());
	        postResponse.setPageSize(posts.getSize());
	        postResponse.setTotalElements(posts.getTotalElements());
	        postResponse.setTotalPages(posts.getTotalPages());
	        postResponse.setLast(posts.isLast());

	        return postResponse;
	    }

	    // convert Entity into DTO
	    private ProjectDto mapToDTO(Project project){
	    	ProjectDto projectDto = new ProjectDto();
	    	projectDto.setId(project.getId());
	    	projectDto.setProject_name(project.getName());
	        return projectDto;
	    }

	    // convert DTO to entity
	    private Project mapToEntity(ProjectDto projectDto){
	    	Project project = new Project();
	    	project.setName(projectDto.getProject_name());
	        return project;
	    }

	


}
