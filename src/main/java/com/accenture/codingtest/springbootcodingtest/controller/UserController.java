package com.accenture.codingtest.springbootcodingtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.codingtest.springbootcodingtest.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService; 

}
