package com.accenture.codingtest.springbootcodingtest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.accenture.codingtest.springbootcodingtest.entity.Task;
import com.accenture.codingtest.springbootcodingtest.entity.exception.RecordNotFoundException;
import com.accenture.codingtest.springbootcodingtest.repository.TaskRepository;
import com.accenture.codingtest.springbootcodingtest.service.TaskService;

@Repository
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public Task save(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public Optional<Task> findById(String id) {
		return taskRepository.findById(id);
	}

	@Override
	public List<Task> findAll() {
		return taskRepository.findAll();
	}

	@Override
	public List<Task> findByProjectId(String projectId) {
		return taskRepository.findByProjectId(projectId);
	}

	@Override
	public List<Task> findByUserId(String userId) {
		return taskRepository.findByUserId(userId);
	}

	@Override
	public Task update(Task task, String id) {
		Task existingTask = taskRepository.findById(id).orElseThrow(RecordNotFoundException::new);
		existingTask.setTitle(task.getTitle());
		existingTask.setDescription(task.getDescription());
		existingTask.setStatus(task.getStatus());

		return taskRepository.save(existingTask);
	}
}