package com.accenture.codingtest.springbootcodingtest.repository;


import org.springframework.data.repository.CrudRepository;

import com.accenture.codingtest.springbootcodingtest.entity.User;



public interface UserRepository extends CrudRepository<User, String> {
}