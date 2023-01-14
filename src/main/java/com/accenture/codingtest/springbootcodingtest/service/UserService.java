package com.accenture.codingtest.springbootcodingtest.service;

import java.util.List;
import java.util.Optional;

import com.accenture.codingtest.springbootcodingtest.entity.User;

public interface UserService {

	List<User> findAll();

	Optional<User> findByUserId(String userId);

	Optional<User> findByUsername(String username);

	User save(User user);

	User update(User user, String userId);

	void delete(String userId);

}