package com.accenture.codingtest.springbootcodingtest.service;

import com.accenture.codingtest.springbootcodingtest.entity.Task;
import com.accenture.codingtest.springbootcodingtest.model.TaskDto;
import com.accenture.codingtest.springbootcodingtest.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import static com.accenture.codingtest.springbootcodingtest.model.Status.NOT_STARTED;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public Collection<TaskDto> findAll() {
        Collection<TaskDto> all = new ArrayList<>();
        taskRepository.findAll().forEach(obj -> {
            all.add(modelMapper.map(obj, TaskDto.class));
        });
        return all;
    }

    public TaskDto findById(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found. Id: " + id));
        return modelMapper.map(task, TaskDto.class);
    }

    public TaskDto create(TaskDto taskDto) {
        Task entity = modelMapper.map(taskDto, Task.class);
        entity.setStatus(NOT_STARTED.name());
        Task createdEntity = taskRepository.save(entity);
        return modelMapper.map(createdEntity, TaskDto.class);
    }

    public TaskDto update(UUID id, TaskDto taskDto) {
        boolean existsById = taskRepository.existsById(id);
        if (existsById) {
            Task entityTask = modelMapper.map(taskDto, Task.class);
            Task updatedEntity = taskRepository.save(entityTask);
            return modelMapper.map(updatedEntity, TaskDto.class);
        }
        throw new EntityNotFoundException("Task not found. Id: " + id);
    }

    public TaskDto updatePartially(UUID id, TaskDto taskDto) {
        TaskDto target = findById(id);
        modelMapper.map(taskDto, target);
        Task targetEntity = modelMapper.map(target, Task.class);
        taskRepository.save(targetEntity);
        return target;
    }

    public void deleteById(UUID id) {
        taskRepository.deleteById(id);
    }
}
