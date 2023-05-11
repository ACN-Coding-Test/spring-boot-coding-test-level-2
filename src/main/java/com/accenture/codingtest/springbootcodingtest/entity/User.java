package com.accenture.codingtest.springbootcodingtest.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name="user09")
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@NotBlank(message = "User ID can not be empty !!")
	private int user_id;
	@NotBlank(message = "User Name can not be empty !!")
	@Column(unique=true)
    private String user_name;
	@NotBlank(message = "Password can not be empty !!")
    private String password;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
   
   
}
