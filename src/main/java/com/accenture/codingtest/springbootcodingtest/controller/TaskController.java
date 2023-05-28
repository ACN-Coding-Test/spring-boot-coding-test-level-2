package com.accenture.codingtest.springbootcodingtest.controller;

import java.util.List;

import com.accenture.codingtest.springbootcodingtest.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/v1/tasks/{role}")
    public  ResponseEntity<Task> saveTask(@RequestBody Task task, @PathVariable("role") String role) {
        ResponseEntity<Task> response = null;
        if(Role.PRODUCT_OWNER.toString().equalsIgnoreCase(role)) {
            response = taskService.saveTask(task);
        } else {
            response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return response;
    }

    @GetMapping("/v1/tasks/{task_id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("task_id") String task_id) {
        return taskService.getTaskById(task_id);
    }

    @PatchMapping("/v1/tasks/{role}")
    public  ResponseEntity<Task> updateTask(@RequestBody Task task,
                                            @PathVariable("userId") String userId,
                                            @PathVariable("role") String role) {
        ResponseEntity<Task> response = null;
        if(Role.PRODUCT_OWNER.toString().equalsIgnoreCase(role)) {
            response = taskService.updateTask(task, userId);
        } else {
            response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return response;
    }

    @PutMapping("/v1/tasks/{role}")
    public  ResponseEntity<Task> updateTask(@RequestBody Task task, @PathVariable("role") String role) {
        ResponseEntity<Task> response = null;
        if(Role.PRODUCT_OWNER.toString().equalsIgnoreCase(role)) {
            response = taskService.updateTask(task);
        } else {
            response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return  response;
    }


    @DeleteMapping("/v1/tasks/{task_id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable("task_id") String task_id) {
        return taskService.deleteTask(task_id);
    }
}
