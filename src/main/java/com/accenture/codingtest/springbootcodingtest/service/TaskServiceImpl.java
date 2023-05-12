package com.accenture.codingtest.springbootcodingtest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.accenture.codingtest.springbootcodingtest.constant.TaskStatus;
import com.accenture.codingtest.springbootcodingtest.entity.Task;
import com.accenture.codingtest.springbootcodingtest.model.TaskModel;
import com.accenture.codingtest.springbootcodingtest.repository.TaskRepo;
import com.accenture.codingtest.springbootcodingtest.utils.IDGenerate;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TaskServiceImpl implements TaskService{

	@Autowired
	TaskRepo taskRepo;
	
	@Autowired
	IDGenerate idGenerate;
	
	@Override
	public TaskModel task(TaskModel taskModel) throws Exception {
		Task taskDomain = new Task();
		UUID id = idGenerate.GenerateId();
		taskModel.setId(id);
		taskModel.setStatus(TaskStatus.NOT_STARTED);
		BeanUtils.copyProperties(taskModel, taskDomain);
		taskRepo.save(taskDomain);
		return taskModel;
	}

	@Override
	public String updateTaskIdempotent(TaskModel taskModel) throws Exception {
		Task taskDomain = new Task();
		BeanUtils.copyProperties(taskModel, taskDomain);
		if(taskRepo.existsById(taskModel.getId()))
		{
			taskRepo.save(taskDomain);
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
		}
		return "Task Updated Successfully";
	}
	

	@Override
	public String updateTask(TaskModel taskModel, Map<String, Object> updates) throws Exception {
	Task taskDomain = new Task();
	taskDomain = taskRepo.getById(taskModel.getId());
	if(taskDomain ==null)
	{
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
	}
	ObjectMapper objectMapper = new ObjectMapper();
	TaskModel taskM = objectMapper.convertValue(updates, TaskModel.class);

	if(taskM.getDescription()!=null)
	{
		taskDomain.setDescription(taskM.getDescription());
		
	}
	if(taskM.getTitle()!=null)
	{
		taskDomain.setTitle(taskM.getTitle());
		
	}
	if(taskM.getStatus()!=null)
	{
		taskDomain.setStatus(taskM.getStatus());
		
	}
	if(taskM.getProject_id()!=null)
	{
		taskDomain.setProject_id(taskM.getProject_id());
		
	}
	if(taskM.getUser_id()!=null)
	{
		taskDomain.setUser_id(taskM.getUser_id());
		
	}
	taskRepo.save(taskDomain);
	return "Task Updated Successfully";
}
	@Override
	public String deleteTaskById(UUID id) throws Exception {
		if(taskRepo.existsById(id))
		{
			taskRepo.deleteById(id);
		}
		else
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
		}
		return "Task Deleted Successfully";
	}

	@Override
	public TaskModel getTaskById(UUID id) throws Exception {
		TaskModel taskModel = new TaskModel(id, null, null, null, id, id);
		Task taskDomain = new Task();
		if(id==null)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task_id is Null");
		}
		else
		{
			taskDomain = taskRepo.getById(id);
			BeanUtils.copyProperties(taskDomain, taskModel);
		}
		return taskModel;
	}

	@Override
	public List<TaskModel> getAllTasks() throws Exception {
	     List<TaskModel> taskModel = new ArrayList<TaskModel>();
	     List<Task> taskDomain = new ArrayList<Task>();
	     taskDomain = taskRepo.findAll();
	     for(Task taskD : taskDomain)
	     {
	    	 TaskModel taskM = new TaskModel(null, null, null, null, null, null);
	    	 BeanUtils.copyProperties(taskD, taskM);
	    	 taskModel.add(taskM);
	     }
	     
		return taskModel;
	}

	

}
