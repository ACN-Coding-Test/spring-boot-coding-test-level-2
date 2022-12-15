package com.accenture.codingtest.springbootcodingtest.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name="projects",
uniqueConstraints={@UniqueConstraint(columnNames ={"name"})})
public class Project {
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
	@Type(type = "uuid-char")
	private UUID id;
	@Column(name="name",nullable=false)
	private String name;
	
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Project [id=" + id + ", name=" + name + "]";
	}
	public Project(UUID id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Project() {
	}
	
	

	
	
	
	

}
