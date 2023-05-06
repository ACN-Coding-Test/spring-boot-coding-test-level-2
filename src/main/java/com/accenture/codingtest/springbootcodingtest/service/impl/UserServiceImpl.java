package com.accenture.codingtest.springbootcodingtest.service.impl;

import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.model.UserDTO;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepository;
import com.accenture.codingtest.springbootcodingtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUser(UUID userId) {
        return userRepository.findById(userId)
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getPassword(), "ROLE_".concat(user.getRole())))
                .orElseThrow(() -> new NoSuchElementException("User was not found with ID : " + userId));
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userRepository.save(new User(userDTO.getUsername(), bCryptPasswordEncoder.encode(userDTO.getPassword()),
                userDTO.getRole()));
        return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
    }

    @Override
    public UserDTO updateUserByPUT(UUID userId, UserDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User was not found with ID : " + userId));
        user.setUsername(userDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setRole("ROLE".concat(userDTO.getRole()));
        User updatedUser = userRepository.save(user);
        return new UserDTO(updatedUser.getId(), updatedUser.getUsername(), updatedUser.getPassword(), updatedUser.getRole());
    }

    @Override
    public ResponseEntity<?> updateUserByPatch(UUID userId, UserDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User was not found with ID : " + userId));
        if (Objects.nonNull(userDTO.getUsername())) {
            if (!userDTO.getUsername().trim().isEmpty()) {
                user.setUsername(userDTO.getUsername());
            } else {
                return new ResponseEntity<>("username should not be empty", HttpStatus.BAD_REQUEST);
            }
        }

        if (Objects.nonNull(userDTO.getPassword())) {
            if (!userDTO.getPassword().trim().isEmpty()) {
                user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
            } else {
                return new ResponseEntity<>("password should not be empty", HttpStatus.BAD_REQUEST);
            }
        }

        if (Objects.nonNull(userDTO.getRole())) {
            if (!userDTO.getRole().trim().isEmpty()) {
                user.setRole("ROLE".concat(userDTO.getRole()));
            } else {
                return new ResponseEntity<>("role should not be empty", HttpStatus.BAD_REQUEST);
            }
        }
        User updatedUser = userRepository.save(user);
        return new ResponseEntity<>(new UserDTO(updatedUser.getId(), updatedUser.getUsername(), updatedUser.getPassword(), updatedUser.getRole()), HttpStatus.OK);
    }

    @Override
    public void deleteUser(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new EmptyResultDataAccessException("User was not found with ID : " + userId, 0);
        }
        userRepository.deleteById(userId);
    }
}
