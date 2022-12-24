package com.accenture.codingtest.springbootcodingtest.service;

import com.accenture.codingtest.springbootcodingtest.entity.Users;
import com.accenture.codingtest.springbootcodingtest.enums.Roles;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users createUser(Users user, String role) {
        if(StringUtils.equals(Roles.ADMIN.toString(), role)) {
            user.setId(user.getId());
            userRepository.save(user);
            return user;
        } else {
            return null;
        }
    }

    public List<Users> getAllUsers(String role) {
        if(StringUtils.equals(Roles.ADMIN.toString(), role)) {
            return userRepository.findAll();
        }
        return new ArrayList<>();
    }

    public Users getUserById(UUID id, String role) {
        if(StringUtils.equals(Roles.ADMIN.toString(), role)) {
            return userRepository.findById(id).orElse(null);
        } else {
            return null;
        }
    }

    public Users updateUser(Users user, UUID id, String role) {
        Users users = userRepository.findById(id).orElse(null);
        if(StringUtils.equals(Roles.ADMIN.toString(), role)) {
            users.setUserName(user.getUserName());
            users.setPassword(user.getPassword());
            userRepository.save(users);
            return users;
        } else {
            return null;
        }
    }

    public String updateUserPassword(Users user, UUID id, String role) {
        Users users = userRepository.findById(id).orElse(null);
        if(StringUtils.equals(Roles.ADMIN.toString(), role)) {
            users.setPassword(user.getPassword());
            userRepository.save(users);
            return "Your password has been set successfully";
        } else {
            return "You are not authorized to perform this operation";
        }

    }

    public String deleteUser(UUID id, String role) {
        if(StringUtils.equals(Roles.ADMIN.toString(), role)) {
            userRepository.deleteById(id);
            return "User has been deleted successfully";
        } else {
            return "You are not authorized to perform this operation";
        }
    }
}
