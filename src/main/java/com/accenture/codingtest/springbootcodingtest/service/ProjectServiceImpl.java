package com.accenture.codingtest.springbootcodingtest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.model.ProjectModel;
import com.accenture.codingtest.springbootcodingtest.repository.ProjectRepo;
import com.accenture.codingtest.springbootcodingtest.utils.IDGenerate;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	ProjectRepo projectRepo;
	
	@Autowired
	IDGenerate idGenerate;
	

	@Override
	public ProjectModel project(ProjectModel projetModel) throws Exception {
		Project projectDomain = new Project();
		UUID id = idGenerate.GenerateId();
		projetModel.setId(id);
		BeanUtils.copyProperties(projetModel, projectDomain);
		projectRepo.save(projectDomain);
		return projetModel;
	}

	@Override
	public String updateProjectIdempotent(ProjectModel projectModel) throws Exception {
		Project projectDomain = new Project();
		BeanUtils.copyProperties(projectModel, projectDomain);
		if(projectRepo.existsById(projectModel.getId()))
		{
			projectRepo.save(projectDomain);
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
		}
		return "Project Updated Successfully";
	}

	@Override
	public String updateProjects(ProjectModel projectModel, Map<String, Object> updates) throws Exception {
		Project projectDomain = new Project();
		projectDomain = projectRepo.getById(projectModel.getId());
		if(projectDomain ==null)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
		}
		ObjectMapper objectMapper = new ObjectMapper();
		ProjectModel projectM = objectMapper.convertValue(updates, ProjectModel.class);

		if(projectM.getName()!=null)
		{
			projectDomain.setName(projectM.getName());
		}
		projectRepo.save(projectDomain);
		return "Project Updated Successfully";
	}

	@Override
	public String deleteProjectById(UUID id) throws Exception {
		if(projectRepo.existsById(id))
		{
			projectRepo.deleteById(id);
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
		}
		return "Project Deleted Successfully";
	}

	@Override
	public ProjectModel getProjectById(UUID id) throws Exception {
		ProjectModel projectModel = new ProjectModel();
		Project projectDomain = new Project();
		if(id==null)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project_id is Null");
		}
		else
		{
			projectDomain = projectRepo.getById(id);
			BeanUtils.copyProperties(projectDomain, projectModel);
		}
		return projectModel;
	}

	@Override
	public List<ProjectModel> getAllProjects() throws Exception {
	     List<ProjectModel> projectModel = new ArrayList<ProjectModel>();
	     List<Project> projectDomain = new ArrayList<Project>();
	     projectDomain = projectRepo.findAll();
	     for(Project projectD : projectDomain)
	     {
	    	 ProjectModel projectM = new ProjectModel();
	    	 BeanUtils.copyProperties(projectD, projectM);
	    	 projectModel.add(projectM);
	     }
	     
		return projectModel;
	}

}
