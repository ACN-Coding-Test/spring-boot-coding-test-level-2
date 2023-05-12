package com.accenture.codingtest.springbootcodingtest.model;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskStatusModel implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3754540549554061689L;
	
	private int sid;
	private String status;

}
