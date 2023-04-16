package com.accenture.codingtest.springbootcodingtest.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import org.mindrot.jbcrypt.BCrypt;

import com.accenture.codingtest.springbootcodingtest.constant.RoleConstants;
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
	    userModel.setIsActive(true);
	    userModel.setRole(RoleConstants.DEFAULT_ROLE);
		BeanUtils.copyProperties(userModel, userDomain);
		userRepo.save(userDomain);
		return userModel;
	}
	
	public static String encode(String password) {
	      String salt = BCrypt.gensalt();
	      return BCrypt.hashpw(password, salt);
	   }
	
	@Override
	public String giveaccessToUser(@PathVariable UUID id, @PathVariable String UserRole, Principal principal) {
		UserModel userModel = new UserModel();
		User userDomain = userRepo.getById(id);
		BeanUtils.copyProperties(userDomain, userModel);
		List<String> activeRoles = getRolesByLoggedInUser(principal);
		String newRole = "";
		if(activeRoles.contains(UserRole)) {
			newRole = userModel.getRole() +","+UserRole;
			userModel.setRole(newRole);	
		}
		User userD = new User();
		BeanUtils.copyProperties(userModel, userD);
		userRepo.save(userD);
		return "New Role has been assigned to "+userModel.getUsername()+" by "+principal.getName();
	}
		
	private List<String> getRolesByLoggedInUser(Principal principal){
		
		String roles = getLoggedInUser(principal).getRole();
		List<String> assignRoles = Arrays.stream(roles.split(",")).collect(Collectors.toList());
		if(assignRoles.contains("ROLE_ADMIN")) {
			return Arrays.stream(RoleConstants.ADMIN_ACCESS).collect(Collectors.toList());
		}
		if(assignRoles.contains("ROLE_PRODUCT_OWNER")) {
			return Arrays.stream(RoleConstants.PRODUCT_OWNER_ACCESS).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}
	
	private UserModel getLoggedInUser(Principal principal) {
		UserModel userM = new UserModel();
		User userD =  userRepo.findByUsername(principal.getName()).get();
		BeanUtils.copyProperties(userD, userM);
		return userM;
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

