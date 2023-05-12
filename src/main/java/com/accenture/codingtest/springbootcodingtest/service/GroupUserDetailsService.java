package com.accenture.codingtest.springbootcodingtest.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.model.UserModel;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepo;

@Service
public class GroupUserDetailsService  implements UserDetailsService{
	
	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userD = userRepo.findByUsername(username);
		Optional<UserModel> userM = null ;
		BeanUtils.copyProperties(userD, userM);
		return userD.map(GroupUserDetails :: new)
				.orElseThrow(()-> new UsernameNotFoundException(username +"User Doesnt Exist"));
	}

}
   