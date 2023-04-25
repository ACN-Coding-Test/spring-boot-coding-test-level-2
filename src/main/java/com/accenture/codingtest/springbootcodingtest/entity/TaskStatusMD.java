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
public class TaskStatusMD implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6328607138767139580L;
	@Id
	@Column(name = "sid")
	private int sid;
	
	@Column(name = "status")
	private String status;
	
	

}
