package com.accenture.codingtest.springbootcodingtest.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Task {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;
	
	@Column(nullable = false)
	private String title;
	
	@Column
	private String description;
	
	@Column(nullable = false)
	private String status;
	
	@JoinColumn(name="id", nullable=false)
	private Project project_id;
	
	@JoinColumn(name="id", nullable=false)
	private User user_id;

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

	public Project getProject_id() {
		return project_id;
	}

	public void setProject_id(Project project_id) {
		this.project_id = project_id;
	}

	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	
}
