package com.example.taskmanagement.services;

import com.example.taskmanagement.entities.Task;

import java.util.List;

public interface TaskService {
    Task save(Task task);
    List<Task> findAll();
    List<Task> findByAuthenticatedUser();

}
