package com.accenture.codingtest.springbootcodingtest.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Task")
public class Task {
	@Id
	private String id;
	private String title;
	private String description;
	private String status;
	private String project_id;
	private String user_id;

	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Task(String id, String title, String description, String status, String project_id, String user_id) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.project_id = project_id;
		this.user_id = user_id;
	}

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

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", status=" + status
				+ ", project_id=" + project_id + ", user_id=" + user_id + "]";
	}

}
