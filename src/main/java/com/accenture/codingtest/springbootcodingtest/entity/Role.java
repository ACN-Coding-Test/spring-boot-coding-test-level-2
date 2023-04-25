package com.accenture.codingtest.springbootcodingtest.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1898433577523417600L;

	@Id
	@Column(name = "id")
	private UUID id;

	@Column(name = "username")
	private String username;
	

}
