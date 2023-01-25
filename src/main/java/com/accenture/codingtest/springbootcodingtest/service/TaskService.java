package com.accenture.codingtest.springbootcodingtest.service;

import com.accenture.codingtest.springbootcodingtest.entity.Task;
import com.accenture.codingtest.springbootcodingtest.model.Role;
import com.accenture.codingtest.springbootcodingtest.model.Status;
import com.accenture.codingtest.springbootcodingtest.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public ResponseEntity<Task> saveTask(Task task) {
        ResponseEntity<Task> response = null;
        if (task != null) {
            //initial status
            task.setStatus(Status.NOT_STARTED.toString());
           response = new ResponseEntity<>(taskRepository.save(task), HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
      return response;
    }
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<>(taskRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Task> getTaskById(String id) {
        return new ResponseEntity<>(taskRepository.findById(id).get(), HttpStatus.OK);
    }

    public ResponseEntity<Task> updateTask(Task task) {
        ResponseEntity<Task> updatedTask = null;
        String id = task.getId();
        Optional<Task> oldTaskOp = taskRepository.findById(id);

        if(oldTaskOp.isPresent()) {
            Task oldTask = oldTaskOp.get();

            oldTask.setDescription(task.getDescription());
            oldTask.setTitle(task.getTitle());
            oldTask.setStatus(task.getStatus());
            oldTask.setUser_id(task.getUser_id());
           // oldTask.setProject_id(task.getProject_id());

            updatedTask = new ResponseEntity<>(taskRepository.save(oldTask), HttpStatus.OK);
        } else {
            updatedTask = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return updatedTask;
    }
    public ResponseEntity<Task> updateTask(Task task, String userId) {
        ResponseEntity<Task> response = null;
        Optional<Task> taskRes = taskRepository.findById(task.getId());
        if (taskRes.isPresent()) {
            if (taskRes.get().getUser_id().equalsIgnoreCase(userId)) {
                taskRes.get().setStatus(task.getStatus());
                response =new ResponseEntity<>(taskRepository.save(taskRes.get()), HttpStatus.OK);
            }
        }
      return response;
    }

    public ResponseEntity<Void> deleteTask(String id) {
        ResponseEntity<Void> response = null;
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }
}
