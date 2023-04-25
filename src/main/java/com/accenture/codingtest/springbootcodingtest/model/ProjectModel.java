package com.accenture.codingtest.springbootcodingtest.model;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectModel implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1560371548692436354L;

	private UUID id;
	
	private String name;
	

}
