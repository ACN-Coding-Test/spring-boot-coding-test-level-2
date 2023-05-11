package com.accenture.codingtest.springbootcodingtest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.accenture.codingtest.springbootcodingtest.entity.User;

import com.accenture.codingtest.springbootcodingtest.service.UserInterface;

@Repository

public class UserRepository {

	@Autowired
	UserInterface userInterface;
	@Autowired
	User user;
	
	public List<User> getUser() {
		
		List<User> list=(List<User>)userInterface.findAll();
		return list;
		
	}

	public Optional<User> getUserById(int id) {
		
		Optional<User> u=userInterface.findById(id);
		return u;
	}

	public User insertUser(User u) {
		
		User ub=userInterface.save(u);
		return ub;
	}

	public User updateUser(User u,int uid) {
		
		User ub=userInterface.save(u);
		return ub;
	}
	
	public void deleteUser(int uid) {
		
		userInterface.deleteById(uid);
	
	}
}
