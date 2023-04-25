package com.accenture.codingtest.springbootcodingtest.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.accenture.codingtest.springbootcodingtest.model.ProjectModel;

public interface ProjectService {
	
	public ProjectModel project (ProjectModel projetModel)throws Exception;
	
	public String updateProjectIdempotent (ProjectModel projectModel)throws Exception;
	
	public String updateProjects(ProjectModel projectModel, Map<String, Object> updates) throws Exception;
	
	public String deleteProjectById (UUID id)throws Exception;
	
	public ProjectModel getProjectById (UUID id)throws Exception;
	
	public List<ProjectModel>  getAllProjects ()throws Exception;

}
