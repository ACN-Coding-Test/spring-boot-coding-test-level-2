package com.accenture.codingtest.springbootcodingtest.model;

public class JwtResponseModel {
	
	String token;

	public JwtResponseModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JwtResponseModel(String token) {
		super();
		this.token = token;
	}

	@Override
	public String toString() {
		return "JwtResponseModel [token=" + token + "]";
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
