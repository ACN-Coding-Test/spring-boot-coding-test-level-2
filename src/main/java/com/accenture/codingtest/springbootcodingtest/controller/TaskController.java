package com.accenture.codingtest.springbootcodingtest.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.codingtest.springbootcodingtest.model.TaskModel;
import com.accenture.codingtest.springbootcodingtest.service.TaskService;

@RestController
@RequestMapping("/v1")
@SuppressWarnings("rawtypes")
public class TaskController {
	
	@Autowired
	TaskService taskService;
	
		
		@PostMapping(value = "/Task/Task")
		@PreAuthorize("hasAuthority('ROLE_PRODUCT_OWNER')")
		@Secured("ROLE_PRODUCT_OWNER")
		public ResponseEntity<TaskModel> task(@RequestBody TaskModel taskModel) throws Exception {
			TaskModel taskModel1 = taskService.task(taskModel);
	        return new ResponseEntity<>(taskModel1, HttpStatus.CREATED);
	        }
		
		@PutMapping(value = "/Task/updateTaskIdempotent")
		public ResponseEntity<String> updateTaskIdempotent(@PathVariable UUID id) throws Exception{
			TaskModel taskModel = taskService.getTaskById(id);
			return new ResponseEntity<>(taskService.updateTaskIdempotent(taskModel), HttpStatus.OK);
		}
		
		@PatchMapping(value = "/Task/updateTask")
		public ResponseEntity<String> updateTask (@PathVariable UUID id) throws Exception{
			TaskModel taskModel = taskService.getTaskById(id);
			return new ResponseEntity<>(taskService.updateTask(taskModel,null), HttpStatus.OK);
		}
		
		@DeleteMapping(value = "/Task/DeleteTask")
		public ResponseEntity<String> deleteTaskById (@PathVariable UUID id) throws Exception{
			return new ResponseEntity<>(taskService.deleteTaskById(id), HttpStatus.OK);
		}
		
		@GetMapping(value = "/Task/GetTaskById")
		public ResponseEntity<TaskModel> getTaskById (@PathVariable UUID id) throws Exception{
			return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
		}
		
		@GetMapping(value = "/Task/GetAllTasks")
		public ResponseEntity<List<TaskModel>> getAllTasks () throws Exception{
			return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
		}

}
