package com.alterra.ajmc.todoapp;

import com.alterra.ajmc.todoapp.model.Role;
import com.alterra.ajmc.todoapp.model.User;
import com.alterra.ajmc.todoapp.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class TodoappApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoappApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.addRole(new Role(null, "ROLE_USER"));
            userService.addRole(new Role(null, "ROLE_ADMIN"));

            userService.addUser(new User(
                    null, "Test User1", "user1", "admin", new ArrayList<>())
            );
            userService.addUser(new User(
                    null, "Test User2", "user2", "admin", new ArrayList<>())
            );
            userService.addUser(new User(
                    null, "Test User3", "user3", "admin", new ArrayList<>())
            );

            userService.addRoleToUser("user1", "ROLE_ADMIN");
            userService.addRoleToUser("user2", "ROLE_USER");
            userService.addRoleToUser("user3", "ROLE_ADMIN");
            userService.addRoleToUser("user3", "ROLE_USER");

        };
    }

}
