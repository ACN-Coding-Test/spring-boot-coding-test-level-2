package com.accenture.codingtest.springbootcodingtest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.accenture.codingtest.springbootcodingtest.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {

	List<User> findAll();

	Optional<User> findById(String userId);

	Optional<User> findByUsername(String username);

}