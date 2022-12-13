package com.accenture.codingtest.springbootcodingtest.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;

	
	public List<User> findAllUsers(){
		return repository.findAll();
	}
	
	public User findOneUser(UUID id) {
		Optional<User> e = repository.findById(id);
		if (e.isPresent()) {
			return e.get();
		}
		return null;
	}
	
	public void saveUser(User user) {
		repository.save(user);
	}
	
	public ResponseEntity<User> updateUser(UUID id,User user) {
		User existingUser = repository.findById(id).orElseThrow();
		existingUser.setUserName(user.getUserName());
		existingUser.setPassword(user.getPassword());
		existingUser.setRole(user.getRole());
		repository.save(existingUser);
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<User> updateUserPartially(UUID id,  User user) {
		try {
			User existingUser = repository.findById(id).get();
			existingUser.setPassword(user.getPassword());
			return new ResponseEntity<User>(repository.save(user), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public void deleteUser(UUID id) {
		repository.deleteById(id);
	}
}
