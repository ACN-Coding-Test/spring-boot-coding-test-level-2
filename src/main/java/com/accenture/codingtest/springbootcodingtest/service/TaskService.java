package com.accenture.codingtest.springbootcodingtest.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.accenture.codingtest.springbootcodingtest.model.TaskModel;

public interface TaskService {
	
	public TaskModel task (TaskModel taskModel)throws Exception;
	
	public String updateTaskIdempotent (TaskModel taskModel)throws Exception;
	
	public String updateTask(TaskModel taskModel, Map<String, Object> updates) throws Exception;
	
	public String deleteTaskById (UUID id)throws Exception;
	
	public TaskModel getTaskById (UUID id)throws Exception;
	
	public List<TaskModel>  getAllTasks ()throws Exception;

}
