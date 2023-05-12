package com.accenture.codingtest.springbootcodingtest.model;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleModel implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1322907224617771740L;

	private UUID id;

	private String username;
	
	

}
