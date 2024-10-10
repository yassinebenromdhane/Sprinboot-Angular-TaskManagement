package com.example.taskmanagement.services;

import com.example.taskmanagement.dtos.UserResponse;
import com.example.taskmanagement.entities.User;
import com.example.taskmanagement.exceptions.UserAlreadyExistsException;
import com.example.taskmanagement.repositories.UserRepository;
import com.example.taskmanagement.utils.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserServiceImp(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;

    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    @Transactional
    public ResponseEntity<UserResponse> save(User user) {
        User newUser = userRepository.findByEmail(user.getEmail());
//        if (newUser != null) {
//            throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists.");
//        }
        if (newUser != null) {
            UserResponse response = new UserResponse("User already exists", null);
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(new UserResponse("User successfully saved", savedUser));
    }

    @Override
    public UserDetails findUserDetailsByEmail(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        user.getRole().getPrivileges().forEach(privilege ->
                authorities.add(new SimpleGrantedAuthority(privilege.getName()))
        );
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public String authenticate(String email, String password) {
        UserDetails userDetails = findUserDetailsByEmail(email);
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            return jwtUtil.generateToken(userDetails);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
