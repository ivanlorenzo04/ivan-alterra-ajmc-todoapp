package com.alterra.ajmc.todoapp.service;

import com.alterra.ajmc.todoapp.model.Role;
import com.alterra.ajmc.todoapp.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<Object> getUsers();
    ResponseEntity<Object> getUser(String username);
    ResponseEntity<Object> addUser(User user);
    ResponseEntity<Object> deleteUser(Long id);
    ResponseEntity<Object> addRole(Role role);
    ResponseEntity<Object> addRoleToUser(String username, String roleName);

}
