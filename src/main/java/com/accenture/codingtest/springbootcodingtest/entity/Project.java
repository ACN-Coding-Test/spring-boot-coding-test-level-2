package com.accenture.codingtest.springbootcodingtest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;
@Component
@Entity

public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotBlank(message = "Project ID can not be empty !!")
	private int id;
	@Column(unique=true)
	@NotBlank(message = "Name can not be empty !!")
	private String name;
	@Lob
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
