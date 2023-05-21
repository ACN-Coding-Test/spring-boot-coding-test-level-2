package com.accenture.codingtest.springbootcodingtest.controller;

import com.accenture.codingtest.springbootcodingtest.entity.Task;
import com.accenture.codingtest.springbootcodingtest.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "api/v1/tasks")
public class TaskController {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private Task fetchTask(UUID taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with Id: " + taskId));
        return task;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> taskList = taskRepository.findAll();
        return ResponseEntity.ok(taskList);
    }

    @GetMapping("/{task_id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("task_id") UUID taskId) {
        Task task = fetchTask(taskId);
        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task savedTask = taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{task_id}")
    public ResponseEntity<Task> updateTask(@PathVariable("task_id") UUID taskId,
                                           @RequestBody Task task) {
        Task foundTask = fetchTask(taskId);

        foundTask.setTitle(task.getTitle());
        foundTask.setDescription(task.getDescription());
        foundTask.setStatus(task.getStatus());
        foundTask.setProject(task.getProject());
        foundTask.setUser(task.getUser());

        Task updatedTask = taskRepository.save(foundTask);
        return ResponseEntity.ok(updatedTask);
    }

    @PatchMapping("/{task_id}")
    public ResponseEntity<Task> patchTask(@PathVariable("task_id") UUID taskId,
                                          @RequestBody Task task) {
        Task foundTask = fetchTask(taskId);

        if (task.getTitle() != null) {
            foundTask.setTitle(task.getTitle());
        }
        if (task.getDescription() != null) {
            foundTask.setDescription(task.getDescription());
        }
        if (task.getStatus() != null) {
            foundTask.setStatus(task.getStatus());
        }
        if (task.getProject() != null) {
            foundTask.setProject(task.getProject());
        }
        if (task.getUser() != null) {
            foundTask.setUser(task.getUser());
        }

        Task patchedTask = taskRepository.save(foundTask);
        return ResponseEntity.ok(patchedTask);
    }

    @DeleteMapping("/{task_id}")
    public ResponseEntity<Task> deleteTask(@PathVariable("task_id") UUID taskId) {
        Task foundTask = fetchTask(taskId);
        taskRepository.delete(foundTask);
        return ResponseEntity.noContent().build();
    }


}
