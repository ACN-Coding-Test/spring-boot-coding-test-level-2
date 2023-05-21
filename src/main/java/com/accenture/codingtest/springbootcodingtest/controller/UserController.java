package com.accenture.codingtest.springbootcodingtest.controller;


import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User fetchUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with userId: " + userId));
        return user;
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList = userRepository.findAll();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUserById(@PathVariable("user_id") UUID userId) {
        User user = fetchUser(userId);
        return ResponseEntity.ok(user);
    }



    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<User> updateUser(@PathVariable("user_id") UUID userId,
                                           @RequestBody User user){
        User foundUser = fetchUser(userId);
        foundUser.setUsername(user.getUsername());
        foundUser.setPassword(user.getPassword());
        User updatedUser = userRepository.save(foundUser);
        return ResponseEntity.ok(updatedUser);
    }

    @PatchMapping("/{user_id}")
    public ResponseEntity<User> patchUser(@PathVariable("user_id") UUID userId,
                                          @RequestBody User user){
        User foundUser = fetchUser(userId);
        if (user.getUsername() != null){
            foundUser.setUsername(user.getUsername());
        }
        if (user.getPassword() != null){
            foundUser.setPassword(user.getPassword());
        }

        User patchedUser = userRepository.save(foundUser);
        return ResponseEntity.ok(patchedUser);

    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<User> deleteUser(@PathVariable("user_id") UUID userId){
        User foundUser = fetchUser(userId);
        userRepository.delete(foundUser);
        return ResponseEntity.noContent().build();
    }




}
