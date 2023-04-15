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

}
