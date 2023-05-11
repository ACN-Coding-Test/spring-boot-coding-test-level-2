package com.accenture.codingtest.springbootcodingtest.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.codingtest.springbootcodingtest.entity.User;



public interface UserInterface extends JpaRepository<User, UUID>{

	Optional<User> findByUsername(String username);
}
