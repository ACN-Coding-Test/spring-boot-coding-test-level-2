package com.accenture.codingtest.springbootcodingtest.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.entity.exception.RecordNotFoundException;
import com.accenture.codingtest.springbootcodingtest.entity.model.UserDto;
import com.accenture.codingtest.springbootcodingtest.service.UserService;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "")
	public List<UserDto> findAll() {
		return userService.findAll().stream().map(this::convertUserEntityToUserDto).collect(Collectors.toList());
	}

	@GetMapping(value = "/{userId}")
	public UserDto findByUserId(@PathVariable String userId) {
		return userService.findByUserId(userId).map(this::convertUserEntityToUserDto)
				.orElseThrow(RecordNotFoundException::new);
	}

	@PostMapping("")
	public UserDto create(@RequestBody UserDto userDto) {
		return this.convertUserEntityToUserDto(userService.save(this.convertUserDtoToUserEntity(userDto)));
	}

	@PutMapping("/{userId}")
	public UserDto updateIdempotent(@RequestBody UserDto userDto, @PathVariable String userId) {
		return this.convertUserEntityToUserDto(userService.update(this.convertUserDtoToUserEntity(userDto), userId));
	}

	@PatchMapping("/{userId}")
	public UserDto update(@RequestBody UserDto userDto, @PathVariable String userId) {
		return this.convertUserEntityToUserDto(userService.update(this.convertUserDtoToUserEntity(userDto), userId));
	}

	@DeleteMapping("/{userId}")
	public void delete(@PathVariable String userId) {
		userService.delete(userId);
	}

	private UserDto convertUserEntityToUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setUsername(user.getUsername());

		return userDto;
	}

	private User convertUserDtoToUserEntity(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());

		return user;
	}

}