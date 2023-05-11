package com.accenture.codingtest.springbootcodingtest.entity;


import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotBlank(message = "Task ID can not be empty !!")
    private int taskId;
    @NotBlank(message = "Title can not be empty !!")
    private String title;
    @Lob
    private String description;
    @NotBlank(message = "Status can not be empty !!")
    private String status;
    @NotBlank(message = "Project Id can not be empty !!")
    private UUID project_id;
    @NotBlank(message = "User Id can not be empty !!")
    
    private UUID user_id;
    
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
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
	public @NotBlank(message = "Project Id can not be empty !!") UUID getProject_id() {
		return project_id;
	}
	public void setProject_id(UUID uuid) {
		this.project_id = uuid;
	}
	public @NotBlank(message = "User Id can not be empty !!") UUID getUser_id() {
		return user_id;
	}
	public void setUser_id(UUID uuid) {
		this.user_id = uuid;
	}
    



    
}
