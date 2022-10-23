package com.alterra.ajmc.todoapp.service.impl;

import com.alterra.ajmc.todoapp.model.User;
import com.alterra.ajmc.todoapp.repository.UserRepository;
import com.alterra.ajmc.todoapp.service.UserTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserTodoServiceImpl implements UserTodoService {

    @Autowired
    private UserRepository userRepository;


}
