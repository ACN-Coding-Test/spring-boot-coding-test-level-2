package com.accenture.codingtest.springbootcodingtest.controller;


import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList = userService.findAll();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUserById(@PathVariable("user_id") UUID userId) {
        User user = userService.findById(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<User> updateUser(@PathVariable("user_id") UUID userId,
                                           @RequestBody User user){

        User updatedUser = userService.updateUser(userId,user);
        return ResponseEntity.ok(updatedUser);
    }

    @PatchMapping("/{user_id}")
    public ResponseEntity<User> patchUser(@PathVariable("user_id") UUID userId,
                                          @RequestBody User user){
        User patchedUser = userService.patchUser(userId,user);
        return ResponseEntity.ok(patchedUser);

    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<User> deleteUser(@PathVariable("user_id") UUID userId){
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }

}
