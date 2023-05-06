package com.accenture.codingtest.springbootcodingtest.service;

import com.accenture.codingtest.springbootcodingtest.model.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO getUser(UUID userId);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUserByPUT(UUID userId, UserDTO userDTO);

    ResponseEntity<?> updateUserByPatch(UUID userId, UserDTO userDTO);

    void deleteUser(UUID userId);
}
