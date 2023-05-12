package com.accenture.codingtest.springbootcodingtest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.service.ProjectInterface;

@Repository
public class ProjectRepository   {

	@Autowired
	ProjectInterface projectInterface;
	@Autowired
	Project project;
	

	public Project insertProject(Project u) {
		
	Project p=projectInterface.save(u);
		return p;
	}
}
