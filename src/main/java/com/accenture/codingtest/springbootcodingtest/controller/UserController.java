package com.accenture.codingtest.springbootcodingtest.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.accenture.codingtest.springbootcodingtest.model.User;
import com.accenture.codingtest.springbootcodingtest.service.UserService;


@Controller("/api/v1/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
//	GET retrieve all resources (ex. GET /api/v1/users)
	@GetMapping
	public List<User> retrieveAllUsers() {
		return userService.findAll();
	}
	

//	GET retrieve one resource by id (ex. GET /api/v1/users/{user_id})
	@GetMapping("/{user_id}")
	public Optional<User> getUserById(@PathVariable("user_id") UUID id) {
		return userService.findById(id);
	}
//	POST create one resource (ex. POST /api/v1/users)
	
	@PostMapping
	private void createUser(@RequestBody User user) {
		userService.createUser(user);
	}
//	PUT update one resource idempotent (ex. PUT /api/v1/users/{user_id})
	@PutMapping("/{user_id}")
	private void updateUser(@RequestBody User user, @PathVariable("user_id") UUID id) {
		userService.updateUser(user, id);
	}
	
	
//	PATCH update one resource (ex. PATCH /api/v1/users/{user_id})
	@PatchMapping("/{user_id}")
	private void patchUser(@RequestBody User user, @PathVariable("user_id") UUID id) {
		userService.patchUser(user, id);
	}
//	DELETE remove one resource (ex. DELETE /api/v1/users/{user_id})
	@DeleteMapping("/{user_id}")
	private void deleteUser(@PathVariable("user_id") UUID id) {
		userService.deleteUser(id);
	}

}
