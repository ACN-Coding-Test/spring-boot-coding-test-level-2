package com.accenture.codingtest.springbootcodingtest.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8861012910247808543L;

	@Id
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "project_id")
	private UUID project_id;
	
	@Column(name = "user_id")
	private UUID user_id;

}
