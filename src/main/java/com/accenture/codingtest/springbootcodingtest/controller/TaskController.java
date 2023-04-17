package com.accenture.codingtest.springbootcodingtest.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.accenture.codingtest.springbootcodingtest.model.Task;
import com.accenture.codingtest.springbootcodingtest.model.Task;
import com.accenture.codingtest.springbootcodingtest.service.TaskService;


@Controller("/api/v1/tasks")
public class TaskController {
	
	@Autowired
	private TaskService taskService;

	public TaskController(TaskService taskService) {
		super();
		this.taskService = taskService;
	}
//	GET retrieve all resources (ex. GET /api/v1/tasks)
	@GetMapping
	public List<Task> retrieveAllTasks() {
		return taskService.findAll();
	}
	

//	GET retrieve one resource by id (ex. GET /api/v1/tasks/{task_id})
	@GetMapping("/{task_id}")
	public Optional<Task> getTaskById(@PathVariable("task_id") UUID id) {
		return taskService.findById(id);
	}
//	POST create one resource (ex. POST /api/v1/tasks)
	
	@PostMapping
	private void createTask(@RequestBody Task task) {
		taskService.createTask(task);
	}
//	PUT update one resource idempotent (ex. PUT /api/v1/tasks/{task_id})
	@PutMapping("/{task_id}")
	private void updateTask(@RequestBody Task task, @PathVariable("task_id") UUID id) {
		taskService.updateTask(task, id);
	}
	
	
//	PATCH update one resource (ex. PATCH /api/v1/tasks/{task_id})
	@PatchMapping("/{task_id}")
	private void patchTask(@RequestBody Task task, @PathVariable("task_id") UUID id) {
		taskService.patchTask(task, id);
	}
//	DELETE remove one resource (ex. DELETE /api/v1/tasks/{task_id})
	@DeleteMapping("/{task_id}")
	private void deleteTask(@PathVariable("task_id") UUID id) {
		taskService.deleteTask(id);
	}

}
