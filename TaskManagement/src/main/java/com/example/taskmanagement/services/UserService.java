package com.example.taskmanagement.services;

import com.example.taskmanagement.dtos.UserResponse;
import com.example.taskmanagement.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    User findByEmailAndPassword(String email, String password);
    ResponseEntity<UserResponse> save(User user);
    UserDetails findUserDetailsByEmail(String email);
    User findUserByEmail(String email);
    String authenticate(String email, String password);
    List<User> findAll();
}
