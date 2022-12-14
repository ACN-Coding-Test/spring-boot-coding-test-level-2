package com.accenture.codingtest.springbootcodingtest.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accenture.codingtest.springbootcodingtest.entity.Task;
import com.accenture.codingtest.springbootcodingtest.repository.TaskRepository;


@Service
public class TaskService {
	
	@Autowired
	TaskRepository repository;

	
	public List<Task> findAllTasks(){
		return repository.findAll();
	}
	
	public Task findOneTask(UUID id) {
		Optional<Task> e = repository.findById(id);
		if (e.isPresent()) {
			return e.get();
		}
		return null;
	}
	
	public void saveTask(Task task) {
		repository.save(task);
	}
	
	public ResponseEntity<Task> updateTask(UUID id,Task task) {
		Task existingTask = repository.findById(id).orElseThrow();
		existingTask.setTitle(task.getTitle());
		existingTask.setProject_id(task.getProject_id());
		existingTask.setStatus(task.getStatus());
		existingTask.setUser_id(task.getUser_id());
		repository.save(existingTask);
		return ResponseEntity.ok().build();
	}
	
	public ResponseEntity<Task> updateTaskPartially(UUID id,  Task task) {
		try {
			Task existingTask= repository.findById(id).get();
			existingTask.setStatus(task.getStatus());
			return new ResponseEntity<Task>(repository.save(task), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public void deleteTask(UUID id) {
		repository.deleteById(id);
	}


}
