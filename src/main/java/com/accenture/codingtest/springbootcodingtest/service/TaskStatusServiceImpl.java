package com.accenture.codingtest.springbootcodingtest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.codingtest.springbootcodingtest.entity.TaskStatusMD;
import com.accenture.codingtest.springbootcodingtest.model.TaskStatusModel;
import com.accenture.codingtest.springbootcodingtest.repository.TaskStatusRepo;

@Service
class TaskStatusServiceImpl implements TaskStatusService{
	
	@Autowired
	TaskStatusRepo taskStatusRepo;

	@Override
	public String addTaskStatus(TaskStatusModel taskStatusModel) throws Exception {
		TaskStatusMD taskStatusDomain = new TaskStatusMD();
		BeanUtils.copyProperties(taskStatusModel, taskStatusDomain);
		taskStatusRepo.save(taskStatusDomain);
		return "Task Status Added Successfully";
	}

	@Override
	public String deleteTaskStatus(String taskStatus) throws Exception {
		taskStatusRepo.deleteByStatus(taskStatus);
		return "Task Status Deleted Successfully";
	}

	@Override
	public List<TaskStatusModel> getTasks() throws Exception {
		List<TaskStatusMD> taskStatusDomain = new ArrayList<>();
		List<TaskStatusModel> taskStatusModel = new ArrayList<TaskStatusModel>();
		taskStatusDomain = taskStatusRepo.findAll();
		BeanUtils.copyProperties(taskStatusDomain, taskStatusModel);
		return taskStatusModel;
	}
	
	

}
