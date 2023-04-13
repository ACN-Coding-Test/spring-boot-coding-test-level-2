package com.accenture.codingtest.springbootcodingtest.controller;

import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.model.Role;
import com.accenture.codingtest.springbootcodingtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/v1/users/{role}")
    public ResponseEntity<List<User>> getAllUsers(@PathVariable("role") String role) {
        ResponseEntity<List<User>> response = null;
        if(Role.ADMIN.toString().equalsIgnoreCase(role)) {
            response = userService.getAllUsers();
        } else {
            response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return response;
    }

    @GetMapping("/v1/users/{user_id}/{role}")
    public ResponseEntity<User> getUserById(@PathVariable("user_id") String user_id,
                                            @PathVariable("role") String role) {
        ResponseEntity<User> response = null;
        if(Role.ADMIN.toString().equalsIgnoreCase(role)) {
            response = userService.getUserById(user_id);
        } else {
            response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return response;
    }

    @PostMapping("/v1/users/{role}")
    public  ResponseEntity<User> saveUser(@RequestBody User user, @PathVariable("role") String role) {

        ResponseEntity<User> response = null;
        if(Role.ADMIN.toString().equalsIgnoreCase(role)) {
            response = userService.saveUser(user);
        } else {
            response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return  response;
    }

    @PutMapping("/v1/users")
    public  ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("role") String role) {
        ResponseEntity<User> response = null;
        if(Role.ADMIN.toString().equalsIgnoreCase(role)) {
            response = userService.updateUser(user);
        } else {
            response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return  response;
    }

    @DeleteMapping("/v1/users/{user_id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("user_id") String user_id,
                                               @PathVariable("role") String role) {
        ResponseEntity<Void> response;
        if(Role.ADMIN.toString().equalsIgnoreCase(role)) {
            response = userService.deleteUser(user_id);
        } else {
            response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return response;
    }
}
