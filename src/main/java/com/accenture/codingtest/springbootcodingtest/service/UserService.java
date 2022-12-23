package com.accenture.codingtest.springbootcodingtest.service;

import com.accenture.codingtest.springbootcodingtest.entity.Users;
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

    public Users createUser(Users user) {
        user.setId(user.getId());
        userRepository.save(user);
        return user;
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users getUserById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    public Users updateUser(Users user, UUID id) {
        Users users = userRepository.findById(id).orElse(null);
        users.setUsername(user.getUserName());
        users.setPassword(user.getPassword());
        userRepository.save(users);
        return users;
    }

    public void updateUserPassword(Users user, UUID id) {
        Users users = userRepository.findById(id).orElse(null);
        users.setPassword(user.getPassword());
        userRepository.save(users);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
