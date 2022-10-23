package com.alterra.ajmc.todoapp.controller;

import com.alterra.ajmc.todoapp.service.UserTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.alterra.ajmc.todoapp.constant.URLConstants.BASE_URL;

@RestController
@RequestMapping(BASE_URL)
public class UserTodoController {

    @Autowired
    private UserTodoService userTodoService;

    @GetMapping
    public ResponseEntity<Object> hello() {
        return ResponseEntity.accepted().body("Hello");
    }

}
