package com.accenture.codingtest.springbootcodingtest.model;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1185129676552210354L;
	
	private UUID id;

	private String username;

	private String password;
	

}
