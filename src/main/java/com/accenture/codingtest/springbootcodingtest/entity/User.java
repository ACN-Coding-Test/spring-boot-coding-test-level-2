package com.accenture.codingtest.springbootcodingtest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "id", length = 40)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;

	@Column(name = "username", length = 100, nullable = false, unique = true)
	private String username;

	@Column(name = "password", length = 150, nullable = false)
	private String password;

}