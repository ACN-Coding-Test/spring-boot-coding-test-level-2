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
public class User implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2716137732048439160L;

	@Id
	@Column(name = "id")
	private UUID id;
	
	@Column(name = "username" ,unique = true)
	private String username;
	
	@Column(name = "password")
	private String password;


}
