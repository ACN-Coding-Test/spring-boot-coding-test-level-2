package com.accenture.codingtest.springbootcodingtest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.codingtest.springbootcodingtest.entity.Task;
import com.accenture.codingtest.springbootcodingtest.service.TaskService;

@RestController
@RequestMapping("/api")
public class TaskController {
	@Autowired
	private TaskService taskService;

	@GetMapping("/v1/tasks")
	public ResponseEntity<List<Task>> getAllTasks() {
		return taskService.getAllTasks();
	}

	@GetMapping("/v1/tasks/{task_id}")
	public ResponseEntity<Task> getTaskById(@PathVariable("task_id") String task_id) {
		return taskService.getTaskById(task_id);
	}

	@DeleteMapping("/v1/tasks/{task_id}")
	public ResponseEntity<Void> deleteTaskById(@PathVariable("task_id") String task_id) {
		return taskService.deleteTask(task_id);
	}
}
