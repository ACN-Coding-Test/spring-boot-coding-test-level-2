package com.accenture.codingtest.springbootcodingtest.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.accenture.codingtest.springbootcodingtest.model.Task;

public interface TaskService {

	List<Task> findAll();

	Optional<Task> findById(UUID id);

	void createTask(Task task);

	void updateTask(Task task, UUID id);

	void patchTask(Task task, UUID id);

	void deleteTask(UUID id);}
