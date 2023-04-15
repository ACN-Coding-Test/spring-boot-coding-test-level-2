package com.accenture.codingtest.springbootcodingtest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.mindrot.jbcrypt.BCrypt;

import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.model.UserModel;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepo;
import com.accenture.codingtest.springbootcodingtest.utils.IDGenerate;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	IDGenerate idGenerate;
	

	@Override
	public UserModel user(UserModel userModel) throws Exception {
		User userDomain = new User();
		UUID id = idGenerate.GenerateId();
		userModel.setId(id);
		String hashpassword = encode(userModel.getPassword());
	    userModel.setPassword(hashpassword);
		BeanUtils.copyProperties(userModel, userDomain);
		userRepo.save(userDomain);
		return userModel;
	}
	
	public static String encode(String password) {
	      String salt = BCrypt.gensalt();
	      return BCrypt.hashpw(password, salt);
	   }

	@Override
	public String updateUserIdempotent(UserModel userModel) throws Exception {
		User userDomain = new User();
		String hashpassword = encode(userModel.getPassword());
	    userModel.setPassword(hashpassword);
		BeanUtils.copyProperties(userModel, userDomain);
		if(userRepo.existsById(userModel.getId()))
		{
			userRepo.save(userDomain);
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}
		return "User Updated Successfully";
	}

	@Override
	public String updateUser(UserModel userModel, Map<String, Object> updates) throws Exception {
		User userDomain = new User();
		String hashpassword = encode(userModel.getPassword());
	    userModel.setPassword(hashpassword);
		userDomain = userRepo.getById(userModel.getId());
		if(userDomain ==null)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}
		ObjectMapper objectMapper = new ObjectMapper();
		UserModel userM = objectMapper.convertValue(updates, UserModel.class);
		

		if(userM.getUsername()!=null)
		{
			userDomain.setUsername(userM.getUsername());
		}
		
		userRepo.save(userDomain);
		return "User Updated Successfully";
	}

	@Override
	public String deleteUserById(UUID id) throws Exception {
		if(userRepo.existsById(id))
		{
			userRepo.deleteById(id);
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}
		return "User Deleted Successfully";
	}

	@Override
	public UserModel getUserById(UUID id) throws Exception {
		UserModel userModel = new UserModel();
		User userDomain = new User();
		if(id==null)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User_id is Null");
		}
		else
		{
			userDomain = userRepo.getById(id);
			BeanUtils.copyProperties(userDomain, userModel);
		}
		
		return userModel;
	}
	@Override
	public List<UserModel> getAllUsers() throws Exception {
	     List<UserModel> userModel = new ArrayList<UserModel>();
	     List<User> userDomain = new ArrayList<User>();
	     userDomain = userRepo.findAll();
	     for(User userD : userDomain)
	     {
	    	 UserModel userM = new UserModel();
	    	 BeanUtils.copyProperties(userD, userM);
	    	 userModel.add(userM);
	     }
	     
		return userModel;
	}
}

