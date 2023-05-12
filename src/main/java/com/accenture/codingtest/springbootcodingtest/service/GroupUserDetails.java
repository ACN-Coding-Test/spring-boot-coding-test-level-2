package com.accenture.codingtest.springbootcodingtest.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.accenture.codingtest.springbootcodingtest.entity.User;


public class GroupUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 751053069909157468L;
	
	private String username;
	private String password;
	private Boolean isActive;
	private List<GrantedAuthority> authorities;

	public GroupUserDetails(User userM) 
	{
		this.username = (String) userM.getUser_name();
		this.password = userM.getPassword();
		this.isActive = userM.getIsActive();
		
		
		this.authorities = Arrays.stream(userM.getRole().split(","))
				.map(SimpleGrantedAuthority ::new).collect(Collectors.toList());
		
		
		
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isActive;
	}

}
