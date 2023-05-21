package com.accenture.codingtest.springbootcodingtest.controller;

import com.accenture.codingtest.springbootcodingtest.entity.Task;
import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.model.TaskStatus;
import com.accenture.codingtest.springbootcodingtest.repository.TaskRepository;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/tasks")
public class TaskController {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasAuthority('PRODUCT_OWNER')")
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
    @PreAuthorize("hasAuthority('PRODUCT_OWNER')")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        task.setStatus(TaskStatus.NOT_STARTED.toString());
        Task savedTask = taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    @PutMapping("/{task_id}")
    @PreAuthorize("hasAuthority('PRODUCT_OWNER')")
    public ResponseEntity<Task> updateTask(@PathVariable("task_id") UUID taskId,
                                           @RequestBody Task updatedTask) {
        Task task = fetchTask(taskId);
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());
        task.setProject(updatedTask.getProject());
        task.setUser(updatedTask.getUser());

        Task savedTask = taskRepository.save(task);
        return ResponseEntity.ok(savedTask);
    }

    @PatchMapping("/{task_id}")
    @PreAuthorize("@securityService.isTaskOwner(#taskId, authentication)")
    public ResponseEntity<Task> patchTask(@PathVariable("task_id") UUID taskId,
                                          @RequestBody Task updatedTask) {
        Task task = fetchTask(taskId);

        if (updatedTask.getTitle() != null) {
            task.setTitle(updatedTask.getTitle());
        }
        if (updatedTask.getDescription() != null) {
            task.setDescription(updatedTask.getDescription());
        }
        if (updatedTask.getStatus() != null) {
            task.setStatus(updatedTask.getStatus());
        }
        if (updatedTask.getProject() != null) {
            task.setProject(updatedTask.getProject());
        }
        if (updatedTask.getUser() != null) {
            task.setUser(updatedTask.getUser());
        }

        Task savedTask = taskRepository.save(task);
        return ResponseEntity.ok(savedTask);
    }

    @DeleteMapping("/{task_id}")
    @PreAuthorize("hasAuthority('PRODUCT_OWNER')")
    public ResponseEntity<Void> deleteTask(@PathVariable("task_id") UUID taskId) {
        Task task = fetchTask(taskId);
        taskRepository.delete(task);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{task_id}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable("task_id") UUID taskId,
                                                 @RequestParam("status") TaskStatus status) {
        Task task = fetchTask(taskId);
        User currentUser = getCurrentUser();

        if (!task.getUser().equals(currentUser)) {
            throw new IllegalArgumentException("You are not authorized to update the task status.");
        }

        task.setStatus(status.toString());

        Task savedTask = taskRepository.save(task);
        return ResponseEntity.ok(savedTask);
    }

    private Task fetchTask(UUID taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with ID: " + taskId));
    }

    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalStateException("No authenticated user found."));
    }
}

