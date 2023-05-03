package com.accenture.codingtest.springbootcodingtest.controller;

import com.accenture.codingtest.springbootcodingtest.model.UserDto;
import com.accenture.codingtest.springbootcodingtest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@Secured("ROLE_ADMIN")
public class UserController {
    private final UserService userService;

    @GetMapping
    public Collection<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable("id") String id) {
        return userService.findById(UUID.fromString(id));
    }

    @PostMapping("/")
    public UserDto create(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable("id") String id, @RequestBody UserDto userDto) {
        return userService.update(UUID.fromString(id), userDto);
    }

    @PatchMapping("/{id}")
    public UserDto updatePartially(@PathVariable("id") String id, @RequestBody UserDto userDto) {
        return userService.updatePartially(UUID.fromString(id), userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") String id) {
        userService.deleteById(UUID.fromString(id));
        return ResponseEntity.ok().build();
    }


}
