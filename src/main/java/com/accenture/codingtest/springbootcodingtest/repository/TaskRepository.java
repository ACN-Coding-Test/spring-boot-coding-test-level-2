package com.accenture.codingtest.springbootcodingtest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.accenture.codingtest.springbootcodingtest.entity.Task;

public interface TaskRepository extends CrudRepository<Task, String> {
	List<Task> findByUserId(String userId);
}
