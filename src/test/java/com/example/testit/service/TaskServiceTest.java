package com.example.testit.service;

import com.example.testit.adapter.mail.MailService;
import com.example.testit.model.Task;
import com.example.testit.model.User;
import com.example.testit.repository.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.util.Assert;

import com.example.testit.repository.UserRepository;
import com.example.testit.service.TaskService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

public class TaskServiceTest {
    TaskService taskService;

    TaskRepository taskRepository;
    UserRepository userRepository;
    MailService mailService;

    @BeforeEach
    void setUp() {
        taskRepository = Mockito.mock(TaskRepository.class); 
        userRepository = Mockito.mock(UserRepository.class);
        mailService = Mockito.mock(MailService.class);

        taskService = new TaskService(taskRepository,userRepository,mailService);
    }

    @Test
    public void test_createTask(){
        var requester_user = new User();
        var assigned_user = new User();

        long requester = 1;
        long assigned = 2;

        requester_user.setId(requester);
        assigned_user.setId(assigned);

        Mockito.when(userRepository.findById(requester)).thenReturn(Optional.of(requester_user));
        Mockito.when(userRepository.findById(assigned)).thenReturn(Optional.of(assigned_user));
        taskService.createTask("titre","description",requester,assigned);
        Mockito.verify(taskRepository).save(any());
    }
}
