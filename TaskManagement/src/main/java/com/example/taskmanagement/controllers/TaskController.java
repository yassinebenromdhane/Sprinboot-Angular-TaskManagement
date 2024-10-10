package com.example.taskmanagement.controllers;

import com.example.taskmanagement.entities.Task;
import com.example.taskmanagement.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/tasks")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public List<Task> findAll() {
        return taskService.findAll();
    }
    @GetMapping("/current-user")
    public List<Task> findByAuthenticatedUser() {
        return taskService.findByAuthenticatedUser();
    }

    @PostMapping("/")
    public Task save(@RequestBody Task task) {
        return taskService.save(task);
    }
}
