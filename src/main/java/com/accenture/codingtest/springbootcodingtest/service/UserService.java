package com.accenture.codingtest.springbootcodingtest.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.accenture.codingtest.springbootcodingtest.model.User;

public interface UserService {

	List<User> findAll();

	void createUser(User user);

	void deleteUser(UUID id);

	void patchUser(User user, UUID id);

	void updateUser(User user, UUID id);

	Optional<User> findById(UUID id);

}
