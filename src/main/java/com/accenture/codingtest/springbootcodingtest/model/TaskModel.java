package com.accenture.codingtest.springbootcodingtest.model;

import java.io.Serializable;
import java.util.UUID;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskModel implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4352086038378546132L;

	private UUID id;
	
	private String title;
	
	private String description;
	
	private String status;

	private UUID project_id;

	private UUID user_id;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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

	public UUID getProject_id() {
		return project_id;
	}

	public void setProject_id(UUID project_id) {
		this.project_id = project_id;
	}

	public UUID getUser_id() {
		return user_id;
	}

	public void setUser_id(UUID user_id) {
		this.user_id = user_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TaskModel(UUID id, String title, String description, String status, UUID project_id, UUID user_id) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.project_id = project_id;
		this.user_id = user_id;
	}

}
