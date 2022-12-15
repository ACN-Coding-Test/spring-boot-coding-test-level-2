package com.accenture.codingtest.springbootcodingtest.controller;

import java.util.List;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.entity.Task;
import com.accenture.codingtest.springbootcodingtest.service.TaskService;


@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
	
	@Autowired
	TaskService service;
	
	@GetMapping
	public List<Task> getAllTasks(){
		return service.findAllTasks();
	}
	
	@GetMapping("/{id}")
	public Task findTaskbyId(@PathVariable UUID id){
		return service.findOneTask(id);
	}
	
	@PostMapping
	public void saveTask(@RequestBody Task task) {
		 service.saveTask(task);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Task> updateProject(@PathVariable UUID id,@RequestBody Task task) {
		return service.updateTask(id,task);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public ResponseEntity<Object> updateTaskPartially(
			@RequestHeader(name = "X-RAM-PERSIST", required = true) String headerPersist,@PathVariable UUID id,
			@RequestBody Task task)
	{
		 service.updateTaskPartially(id,task);
		return new ResponseEntity<>(
				"Task is updated successfully with id = " + task.getId(),
				HttpStatus.CREATED);
	}
	
	/*@PatchMapping("/{id}")
	public ResponseEntity<Task> updateTaskPartially(@PathVariable UUID id,@RequestBody Task task) {
		return service.updateTaskPartially(id,task);
	}*/
	
	@DeleteMapping("/{id}")
	public void deleteTask(@PathVariable(value="id")UUID id) {
		service.deleteTask(id);
	}

}
