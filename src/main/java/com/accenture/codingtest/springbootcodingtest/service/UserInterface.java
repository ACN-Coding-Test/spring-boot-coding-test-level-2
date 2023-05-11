package com.accenture.codingtest.springbootcodingtest.service;

import org.springframework.data.repository.CrudRepository;

import com.accenture.codingtest.springbootcodingtest.entity.User;



public interface UserInterface extends CrudRepository<User, Integer>{

	

}
