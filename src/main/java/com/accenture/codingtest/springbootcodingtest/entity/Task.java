package com.accenture.codingtest.springbootcodingtest.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task {

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "title", length = 250, nullable = false)
	private String title;
	@Column(name = "description")
	private String description;	
	@Column(name = "status", nullable = false)
	private String status;
	@Column(name = "project_id", nullable = false)
	private String projectId;
	@Column(name = "user_id", nullable = false)
	private String userId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}

