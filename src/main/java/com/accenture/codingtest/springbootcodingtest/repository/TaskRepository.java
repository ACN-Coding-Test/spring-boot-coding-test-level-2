package com.accenture.codingtest.springbootcodingtest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.accenture.codingtest.springbootcodingtest.entity.Task;
import com.accenture.codingtest.springbootcodingtest.service.TaskInterface;

@Repository
public class TaskRepository {

	
	@Autowired
	TaskInterface taskInterface;
	@Autowired
	Task task;
	

	public Task insertTask(Task u) {
		
		Task t=taskInterface.save(u);
		return t;
	}
}
