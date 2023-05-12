package com.accenture.codingtest.springbootcodingtest.controller;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.codingtest.springbootcodingtest.model.UserModel;
import com.accenture.codingtest.springbootcodingtest.service.UserService;



@RestController
@RequestMapping("/v1")
@SuppressWarnings("rawtypes")
public class UserController {
	
	@Autowired
	UserService userService;
	

		
		@PostMapping(value = "/User/User")
	//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	//	@Secured("ROLE_ADMIN")
		public ResponseEntity<UserModel>user(@RequestBody UserModel userModel) throws Exception {
			UserModel userModel1 = userService.user(userModel);
	        return new ResponseEntity<>(userModel1, HttpStatus.CREATED);
		}
		
		
		@GetMapping(value = "/User/Access/{userId}/{userRole}")
	//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	//	@Secured("ROLE_ADMIN")
		public ResponseEntity<String> giveaccessToUser(@PathVariable UUID id, @PathVariable String UserRole, Principal principal) throws Exception{
			return new ResponseEntity<>(userService.giveaccessToUser(id, UserRole, principal), HttpStatus.OK);
		}
		
		@PutMapping(value = "/User/updateUserIdempotent")
		@PreAuthorize("hasAuthority('ROLE_ADMIN')")
		@Secured("ROLE_ADMIN")
		public ResponseEntity<String> updateUserIdempotent(@PathVariable UUID id) throws Exception{
			UserModel userModel = userService.getUserById(id);
			return new ResponseEntity<>(userService.updateUserIdempotent(userModel), HttpStatus.OK);
		}
		
		@PatchMapping(value = "/User/updateUser")
		@PreAuthorize("hasAuthority('ROLE_ADMIN')")
		@Secured("ROLE_ADMIN")
		public ResponseEntity<String> updateUser (@PathVariable UUID id) throws Exception{
			UserModel userModel = userService.getUserById(id);
			return new ResponseEntity<>(userService.updateUser(userModel,null), HttpStatus.OK);
		}
		
		@DeleteMapping(value = "/User/DeleteUser")
		@PreAuthorize("hasAuthority('ROLE_ADMIN')")
		@Secured("ROLE_ADMIN")
		public ResponseEntity<String> deleteUserById (@PathVariable UUID id) throws Exception{
			return new ResponseEntity<>(userService.deleteUserById(id), HttpStatus.OK);
		}
		
		@GetMapping(value = "/User/GetUserById")
		@PreAuthorize("hasAuthority('ROLE_ADMIN')")
		@Secured("ROLE_ADMIN")
		public ResponseEntity<UserModel> getProjectById (@PathVariable UUID id) throws Exception{
			return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
		}
		
		@GetMapping(value = "/User/GetAllUsers")
	//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	//	@Secured("ROLE_ADMIN")
		public ResponseEntity<List<UserModel>> getAllUsers () throws Exception{
			return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
		}


}
