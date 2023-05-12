package com.accenture.codingtest.springbootcodingtest.service;

import java.util.List;

import com.accenture.codingtest.springbootcodingtest.model.TaskStatusModel;

public interface TaskStatusService {
	
	public String addTaskStatus (TaskStatusModel taskModel)throws Exception;
	
	public String deleteTaskStatus (String taskStatus) throws Exception;
	
	public List<TaskStatusModel> getTasks ()throws Exception;

}
