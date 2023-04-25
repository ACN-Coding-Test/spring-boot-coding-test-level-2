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
public class Project implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6476940302896158570L;

	@Id
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "name", unique = true)
	private String name; 
	

}
