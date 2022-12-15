package com.accenture.codingtest.springbootcodingtest.controller;

import java.util.List;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	UserService service;
	
	@GetMapping
	public List<User> getAllUsers(){
		return service.findAllUsers();
	}
	
	@GetMapping("/{id}")
	public User findUserbyId(@PathVariable UUID id){
		return service.findOneUser(id);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Object> createUser(
			@RequestHeader(name = "X-RAM-PERSIST", required = true) String headerPersist,
			@RequestBody User user)
	{
		 service.saveUser(user);
		return new ResponseEntity<>(
				"User is created successfully with id = " + user.getUser_id(),
				HttpStatus.CREATED);
	}
	
	/*@PostMapping
	public void saveUser(@RequestBody User user) {
		 service.saveUser(user);
	}*/
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable UUID id,@RequestBody User user) {
		return service.updateUser(id,user);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<User> updateUserPartially(@PathVariable UUID id,@RequestBody User user) {
		return service.updateUserPartially(id,user);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable(value="id")UUID id) {
		service.deleteUser(id);
	}


}
