package com.accenture.codingtest.springbootcodingtest.service;

import com.accenture.codingtest.springbootcodingtest.entity.Task;
import com.accenture.codingtest.springbootcodingtest.entity.Users;
import com.accenture.codingtest.springbootcodingtest.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskService subject;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void changeTaskStatus_ReturnTask() {
        UUID userId = UUID.fromString("df4ca868-6429-48fc-8550-737e5c82cb66");
        String status = "NOT_STARTED";
        Task taskEntity = new Task();
        Users users = new Users();
        users.setId(userId);
        taskEntity.setStatus(status);

        Task taskRequest = new Task();
        taskRequest.setStatus(status);
        when(taskRepository.findTasksByUserId(userId)).thenReturn(taskRequest);
        Task result = subject.updateStatus(taskRequest, userId);
        assertEquals(result, taskRequest);

        verify(taskRepository).findTasksByUserId(userId);
    }
}
