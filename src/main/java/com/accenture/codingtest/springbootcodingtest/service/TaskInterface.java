package com.accenture.codingtest.springbootcodingtest.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.accenture.codingtest.springbootcodingtest.entity.Task;

public interface TaskInterface extends CrudRepository<Task, Integer>{

}
