package com.accenture.codingtest.springbootcodingtest.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@PostMapping
	public void saveUser(@RequestBody User user) {
		 service.saveUser(user);
	}
	
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
