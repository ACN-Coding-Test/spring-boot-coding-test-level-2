package com.accenture.codingtest.springbootcodingtest;

import java.util.UUID;

public class ProjectDto {
	
	private UUID id;
	private String project_name;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	
	

}
