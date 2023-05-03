package com.accenture.codingtest.springbootcodingtest.controller;

import com.accenture.codingtest.springbootcodingtest.model.TaskDto;
import com.accenture.codingtest.springbootcodingtest.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public Collection<TaskDto> findAll() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public TaskDto findById(@PathVariable("id") String id) {
        return taskService.findById(UUID.fromString(id));
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ROLE_PRODUCT_OWNER')")
    public TaskDto create(@RequestBody TaskDto taskDto) {
        return taskService.create(taskDto);
    }

    @PutMapping("/{id}")
    public TaskDto update(@PathVariable("id") String id, @RequestBody TaskDto taskDto) {
        return taskService.update(UUID.fromString(id), taskDto);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PRODUCT_OWNER')")
    public TaskDto updatePartially(@PathVariable("id") String id, @RequestBody TaskDto taskDto) {
        return taskService.updatePartially(UUID.fromString(id), taskDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") String id) {
        taskService.deleteById(UUID.fromString(id));
        return ResponseEntity.ok().build();
    }


}
