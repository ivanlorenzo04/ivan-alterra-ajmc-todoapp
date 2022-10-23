package com.alterra.ajmc.todoapp.controller;

import com.alterra.ajmc.todoapp.form.RoleToUserForm;
import com.alterra.ajmc.todoapp.model.User;
import com.alterra.ajmc.todoapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.alterra.ajmc.todoapp.constant.URLConstants.*;


@RestController
@RequestMapping(BASE_URL)
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(USER_URL)
    public ResponseEntity<Object> getUsers() {
        return userService.getUsers();
    }

    @PostMapping(USER_URL)
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping(USER_URL+"{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @PostMapping(USER_ROLE_URL)
    public ResponseEntity<Object> saveRoleToUser(@RequestBody RoleToUserForm roleToUserForm) {
        return userService.addRoleToUser(roleToUserForm.getUsername(), roleToUserForm.getRoleName());
    }
}
