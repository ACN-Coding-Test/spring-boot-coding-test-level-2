package com.accenture.codingtest.springbootcodingtest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.entity.exception.RecordAlreadyExistException;
import com.accenture.codingtest.springbootcodingtest.entity.exception.RecordNotFoundException;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepository;
import com.accenture.codingtest.springbootcodingtest.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        Optional<User> existingUserOptional = userRepository.findByUsername(user.getUsername());
        if (existingUserOptional.isPresent()) {
            throw new RecordAlreadyExistException();
        }

        return userRepository.save(user);
    }

    @Override
    public User update(User user, String userId) {
        Optional<User> existingUserOptional = userRepository.findById(userId);
        if (existingUserOptional.isEmpty()) {
            throw new RecordNotFoundException();
        }

        User existingUser = existingUserOptional.get();
        existingUser.setUsername(user.getUsername());
        return userRepository.save(existingUser);
    }

    @Override
    public void delete(String userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(RecordNotFoundException::new);
        userRepository.deleteById(existingUser.getId());
    }
}