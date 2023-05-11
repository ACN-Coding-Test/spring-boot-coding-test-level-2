package com.accenture.codingtest.springbootcodingtest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepository;


@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	User user;
	//GET retrieve all resources (ex. GET /api/v1/users)
	@RequestMapping(value="/api/v1/users", method=RequestMethod.GET)
	public ResponseEntity<List<User>> getUser() {
		
		List<User> list=null;
		try {
			 list=(List<User>)userRepository.getUser();
			 if(list.size()<=0) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
					
					}
			 return ResponseEntity.of(Optional.of(list));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	//GET retrieve one resource by id (ex. GET /api/v1/users/{user_id})
	@RequestMapping(value="/api/v1/users/{user_id}", method=RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("user_id") int user_id) {
		
		User u=null;
		try {
			Optional<User> bb=userRepository.getUserById(user_id);
			if(bb.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
				}
			return ResponseEntity.of(bb);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	//POST create one resource (ex. POST /api/v1/users)
	@RequestMapping(value="/api/v1/users", method=RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User u=null;
		try {
			 u=userRepository.insertUser(user);
			 return ResponseEntity.of(Optional.of(u));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	//PUT update one resource idempotent (ex. PUT /api/v1/users/{user_id})
	@RequestMapping(value="/api/v1/users/{user_id}", method=RequestMethod.PUT)
	public ResponseEntity<User> updateUserById( @PathVariable("user_id") int user_id, @RequestBody User user) {
		User u=null;
		
		try {
			 u=userRepository.updateUser(user,user_id);
			 return ResponseEntity.of(Optional.of(u));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	//PATCH update one resource (ex. PATCH /api/v1/users/{user_id})
	@PatchMapping(value="/api/v1/users/{user_id}")
	public ResponseEntity<User> updateUserByIdPatch( @PathVariable("user_id") String user_id, @RequestBody User user) {
		User u=null;
		try {
			u=userRepository.updateUser(user,Integer.parseInt(user_id));
			 return ResponseEntity.of(Optional.of(u));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	//DELETE remove one resource (ex. DELETE /api/v1/users/{user_id})
	@RequestMapping(value="/api/v1/users/{user_id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUserById( @PathVariable("user_id") int user_id) {
	
		try {
			 userRepository.deleteUser(user_id);
			 return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
}
