package com.accenture.codingtest.springbootcodingtest.service;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;

import com.accenture.codingtest.springbootcodingtest.model.UserModel;

public interface UserService {
	
	public UserModel user (UserModel UserModel)throws Exception;
	
	public String giveaccessToUser(@PathVariable UUID id, @PathVariable String UserRole, Principal principal)throws Exception;
	
	public String updateUserIdempotent (UserModel UserModel)throws Exception;
	
	public String updateUser(UserModel UserModel, Map<String, Object> updates) throws Exception;
	
	public String deleteUserById (UUID id)throws Exception;
	
	public UserModel getUserById (UUID id)throws Exception;
	
	public List<UserModel>  getAllUsers ()throws Exception;

	    

}
