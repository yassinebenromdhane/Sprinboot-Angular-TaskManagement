package com.example.taskmanagement.controllers;

import com.example.taskmanagement.dtos.LoginRequest;
import com.example.taskmanagement.dtos.LoginResponse;
import com.example.taskmanagement.dtos.UserResponse;
import com.example.taskmanagement.entities.User;
import com.example.taskmanagement.exceptions.UserAlreadyExistsException;
import com.example.taskmanagement.repositories.UserRepository;
import com.example.taskmanagement.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }


    @GetMapping("/email-password")
    public User findByEmailAndPassword(@RequestParam String email,
                                                 @RequestParam String password) {
        return userService.findByEmailAndPassword(email, password);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> saveUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String token = userService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        if (token != null) {
            User user = userService.findUserByEmail(loginRequest.getEmail());
            return ResponseEntity.ok(new LoginResponse(user, token));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.findAll();
    }
}
