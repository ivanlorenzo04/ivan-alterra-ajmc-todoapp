package com.alterra.ajmc.todoapp.service.impl;

import com.alterra.ajmc.todoapp.model.Role;
import com.alterra.ajmc.todoapp.model.User;
import com.alterra.ajmc.todoapp.repository.RoleRepository;
import com.alterra.ajmc.todoapp.repository.UserRepository;
import com.alterra.ajmc.todoapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static com.alterra.ajmc.todoapp.constant.MessageConstants.*;
import static org.springframework.http.HttpStatus.*;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        } else {

        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public ResponseEntity<Object> getUsers() {
        try {
            List<User> users = userRepository.findAll();
            return ResponseEntity.status(CREATED).body(users);
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(FAILED_GET_USERS + e.getLocalizedMessage());
        }
    }

    @Override
    public ResponseEntity<Object> getUser(String username) {
        try {
            User user = userRepository.findByUsername(username);
            return ResponseEntity.status(OK).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(FAILED_GET_USERS + e.getLocalizedMessage());
        }
    }

    @Override
    public ResponseEntity<Object> addUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return ResponseEntity.status(CREATED).body(SUCCESS_ADD_USER);
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(FAILED_ADD_USER + e.getLocalizedMessage());
        }
    }

    @Override
    public ResponseEntity<Object> deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return ResponseEntity.status(NO_CONTENT).body(SUCCESS_DELETE_USER);
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(FAILED_DELETE_USER + e.getLocalizedMessage());
        }
    }

    @Override
    public ResponseEntity<Object> addRole(Role role) {
        try {
            roleRepository.save(role);
            return ResponseEntity.status(CREATED).body(SUCCESS_ADD_ROLE);
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(FAILED_ADD_ROLE + e.getLocalizedMessage());
        }
    }

    @Override
    public ResponseEntity<Object> addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);

        return ResponseEntity.status(CREATED).body(SUCCESS_ADD_USER_TO_ROLE);
    }

}
