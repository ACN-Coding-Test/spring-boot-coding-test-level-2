package com.accenture.codingtest.springbootcodingtest.controller;

import com.accenture.codingtest.springbootcodingtest.model.UserDTO;
import com.accenture.codingtest.springbootcodingtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    private ResponseEntity<List<UserDTO>> getUserList() {
        if (!userService.getAllUsers().isEmpty()) {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{user_id}")
    private ResponseEntity<UserDTO> getUser(@PathVariable("user_id") UUID userId) {
        UserDTO user = userService.getUser(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<UserDTO> saveUser(@RequestBody @Valid UserDTO userDTO) {
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{user_id}")
    private ResponseEntity<UserDTO> updateUserByPUT(@PathVariable("user_id") UUID userID, @RequestBody @Valid UserDTO userDTO) {
        return new ResponseEntity<>(userService.updateUserByPUT(userID, userDTO), HttpStatus.OK);
    }

    @PatchMapping("/{user_id}")
    private ResponseEntity<?> updateUserByPATCH(@PathVariable("user_id") UUID userID, @RequestBody UserDTO userDTO) {
        return userService.updateUserByPatch(userID, userDTO);
    }

    @DeleteMapping("/{user_id}")
    private ResponseEntity<?> deleteUser(@PathVariable("user_id") UUID userID) {
        userService.deleteUser(userID);
        return new ResponseEntity<>("user deleted successfully!", HttpStatus.OK);
    }
}
