package com.accenture.codingtest.springbootcodingtest.repository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, String> {
	List<Task> findByUserId(String userId);
}