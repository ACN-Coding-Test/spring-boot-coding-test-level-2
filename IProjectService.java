package com.accenture.codingtest.springbootcodingtest.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.accenture.codingtest.springbootcodingtest.ProjectDto;
import com.accenture.codingtest.springbootcodingtest.ProjectResponse;
import com.accenture.codingtest.springbootcodingtest.entity.Project;

@Service
public interface IProjectService {

	List<Project> findPaginated(int pageNo, int pageSize, Sort name);

	List<Project> findPaginated(int pageNo, int pageSize);
	
	ProjectDto createProject(ProjectDto postDto);
	ProjectResponse getAllProjects(int pageNo, int pageSize, String sortBy, String sortDir);

}
