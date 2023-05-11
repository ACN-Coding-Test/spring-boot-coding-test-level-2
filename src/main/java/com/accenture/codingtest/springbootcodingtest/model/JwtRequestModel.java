package com.accenture.codingtest.springbootcodingtest.model;

public class JwtRequestModel {
	
    String username;
	
	String password;

	public JwtRequestModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JwtRequestModel(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "JwtRequestModel [username=" + username + ", password=" + password + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
