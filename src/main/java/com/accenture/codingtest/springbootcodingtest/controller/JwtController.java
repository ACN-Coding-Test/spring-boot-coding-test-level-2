package com.accenture.codingtest.springbootcodingtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.codingtest.springbootcodingtest.filter.JwtUtil;
import com.accenture.codingtest.springbootcodingtest.model.JwtRequestModel;
import com.accenture.codingtest.springbootcodingtest.model.JwtResponseModel;
import com.accenture.codingtest.springbootcodingtest.model.TaskModel;
import com.accenture.codingtest.springbootcodingtest.service.GroupUserDetailsService;

@RestController
public class JwtController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private GroupUserDetailsService groupUserDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping(value = "/Token/GenerateToken")
	public ResponseEntity<?> GenerateToken(@RequestBody JwtRequestModel jwtRequestM) throws Exception {
		try {
			
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequestM.getUsername(), jwtRequestM.getPassword()));
			
		}catch(UsernameNotFoundException e)
		{
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		//fine area
		UserDetails userDetails = this.groupUserDetailsService.loadUserByUsername(jwtRequestM.getUsername());
		
		String token = jwtUtil.generateToken(jwtRequestM.getUsername());
		System.out.println(token);
		
		
        return ResponseEntity.ok( new JwtResponseModel(token));
     //   }
	
	
	}

}
