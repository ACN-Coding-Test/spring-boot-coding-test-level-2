package com.accenture.codingtest.springbootcodingtest.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.accenture.codingtest.springbootcodingtest.model.User;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public List<User> findAll() {

		return userRepository.findAll();
	}

	@Override
	public Optional<User> findById(UUID id) {

		return userRepository.findById(id);
	}

	@Override
	public void createUser(User user) {

		userRepository.save(user);

	}

	@Override
	public void deleteUser(UUID id) {

		Optional<User> usOptional = userRepository.findById(id);
		userRepository.delete(usOptional.get());

	}

	@Override
	public void patchUser(User user, UUID id) {

		Optional<User> usOptional = userRepository.findById(id);
		User user2 = usOptional.get();
		user2.setPassword(user.getPassword());
		user2.setUsername(user.getUsername());
		userRepository.save(user2);

	}

	@Override
	public void updateUser(User user, UUID id) {

		Optional<User> usOptional = userRepository.findById(id);
		User user2 = usOptional.get();
		user2.setPassword(user.getPassword());
		user2.setUsername(user.getUsername());
		userRepository.save(user2);

	}

}
