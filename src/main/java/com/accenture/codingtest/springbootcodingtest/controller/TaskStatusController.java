package com.accenture.codingtest.springbootcodingtest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.codingtest.springbootcodingtest.model.TaskStatusModel;
import com.accenture.codingtest.springbootcodingtest.service.TaskStatusService;

@RestController
@RequestMapping("/v1")
@SuppressWarnings("rawtypes")
public class TaskStatusController {
	
	@Autowired
	TaskStatusService taskStatusService;
	
	@PostMapping(value = "/TaskStatus/AddTaskStatus")
	@PreAuthorize("hasAuthority('ROLE_PRODUCT_OWNER')")
	@Secured("ROLE_PRODUCT_OWNER")
	public ResponseEntity<String> addTaskStatus(@RequestBody TaskStatusModel taskStatusModel) throws Exception {
		String respon = taskStatusService.addTaskStatus(taskStatusModel);
        return new ResponseEntity<>(respon, HttpStatus.CREATED);
        }
	
	@PostMapping(value = "/TaskStatus/deleteTaskStatus")
	public ResponseEntity<String> deleteTaskStatus(@PathVariable String taskStatus) throws Exception {
		String respon = taskStatusService.deleteTaskStatus(taskStatus);
		return new ResponseEntity<>(respon, HttpStatus.OK);
	}
    
	@PostMapping(value = "/TaskStatus/getAllTaskStatus")
	public ResponseEntity<List<TaskStatusModel>> getAllTaskStatus () throws Exception {
		return new ResponseEntity<>(taskStatusService.getTasks(), HttpStatus.OK);
	}
}
