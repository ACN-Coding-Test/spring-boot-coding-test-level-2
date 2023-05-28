package com.accenture.codingtest.springbootcodingtest.service;

import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {


    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        userRepository.save(user);
        return user;
    }

    public User findById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with userId: " + userId));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User updateUser(UUID userId, User user) {
        User fetch = findById(userId);
        fetch.setUsername(user.getUsername());
        fetch.setPassword(user.getPassword());
        return userRepository.save(fetch);
    }

    public User patchUser(UUID userId, User user){
        User fetch = findById(userId);
        if (user.getUsername() != null){
            fetch.setUsername(user.getUsername());
        }
        if (user.getPassword() != null){
            fetch.setPassword(user.getPassword());
        }

        return userRepository.save(fetch);
    }

    public void delete(UUID userId) {
        User fetch = findById(userId);
        userRepository.deleteById(userId);
    }

}
