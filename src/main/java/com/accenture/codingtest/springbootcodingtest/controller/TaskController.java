package com.accenture.codingtest.springbootcodingtest.controller;

import com.accenture.codingtest.springbootcodingtest.entity.Task;
import com.accenture.codingtest.springbootcodingtest.model.TaskStatus;
import com.accenture.codingtest.springbootcodingtest.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> taskList = taskService.findAll();
        return ResponseEntity.ok(taskList);
    }

    @GetMapping("/{task_id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("task_id") UUID taskId) {
        Task task = taskService.findById(taskId);
        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        task.setStatus(TaskStatus.NOT_STARTED);
        Task savedTask = taskService.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    @PutMapping("/{task_id}")
    public ResponseEntity<Task> updateTask(@PathVariable("task_id") UUID taskId,
                                           @RequestBody Task updatedTask) {
        Task task = taskService.updateTask(taskId,updatedTask);
        return ResponseEntity.ok(task);
    }


    @PatchMapping("/{task_id}")
    public ResponseEntity<Task> patchTask(@PathVariable("task_id") UUID taskId,
                                          @RequestBody Task updatedTask) {
        Task task = taskService.patchTask(taskId,updatedTask);

        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{task_id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("task_id") UUID taskId) {

        taskService.delete(taskId);
        return ResponseEntity.noContent().build();
    }


    @PatchMapping("/{task_id}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable("task_id") UUID taskId,
                                                 @RequestParam("status") TaskStatus status) {
        Task task = taskService.updateTaskStatus(taskId,status);
        return ResponseEntity.ok(task);
    }




}

