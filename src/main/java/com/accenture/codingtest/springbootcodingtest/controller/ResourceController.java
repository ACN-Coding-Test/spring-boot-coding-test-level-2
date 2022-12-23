package com.accenture.codingtest.springbootcodingtest.controller;

import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.entity.Task;
import com.accenture.codingtest.springbootcodingtest.entity.Users;
import com.accenture.codingtest.springbootcodingtest.service.ProjectService;
import com.accenture.codingtest.springbootcodingtest.service.TaskService;
import com.accenture.codingtest.springbootcodingtest.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class ResourceController {

    private UserService userService;
    private TaskService taskService;
    private ProjectService projectService;

    public ResourceController(UserService userService, TaskService taskService, ProjectService projectService) {
        this.userService = userService;
        this.taskService = taskService;
        this.projectService = projectService;
    }

    @PostMapping("/users")
    public ResponseEntity<Users> createResource(@RequestBody Users user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Users> updateResource(@RequestBody Users users, @PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(userService.updateUser(users, id));
    }

    @PatchMapping("/users/{id}")
    public String updateOneResource(@RequestBody Users users, @PathVariable(name = "id") UUID id) {
        userService.updateUserPassword(users, id);
        return "Your password has been set successfully";
    }

    @DeleteMapping("/users/{id}")
    public String deleteResource(@PathVariable(name = "id") UUID id) {
        userService.deleteUser(id);
        return "User has been deleted successfully";
    }

    @PostMapping("/task")
    public ResponseEntity<Task> addNewTask(@RequestBody Task task, @RequestParam(name = "userId") UUID userId) {
        return ResponseEntity.ok(taskService.createTask(task, userId));
    }

    @GetMapping("/task/{userId}")
    public ResponseEntity<Task> getTasksOfUsers(@PathVariable(name = "userId") UUID userId) {
        return ResponseEntity.ok(taskService.getTasksOfUser(userId));
    }

    @GetMapping("/tasks/{projectId}")
    public ResponseEntity<List<Task>> getTasksOfProject(@PathVariable(name = "projectId") UUID projectId) {
        return ResponseEntity.ok(taskService.getTaskByProjectId(projectId));
    }

    @PostMapping("/projects")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.createNewProject(project));
    }

    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getAllProjects(@RequestParam(name = "userId") UUID userId) {
        return ResponseEntity.ok(projectService.getAllProjects(userId));
    }
}
