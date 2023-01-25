package com.accenture.codingtest.springbootcodingtest.service;

import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<User> saveUser(User user) {
        ResponseEntity<User> response = null;
        if (user != null) {
           response = new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
      return response;
    }


    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<User> getUserById(String id) {
           return new ResponseEntity<>(userRepository.findById(id).get(), HttpStatus.OK);
    }

    public ResponseEntity<User> updateUser(User user) {
        ResponseEntity<User> updatedUser = null;
        String id = user.getId();
        Optional<User> oldUserOp = userRepository.findById(id);

        if(oldUserOp.isPresent()) {
            User oldUser = oldUserOp.get();

            oldUser.setUsername(user.getUsername());
            oldUser.setPassword(user.getPassword());

            updatedUser = new ResponseEntity<>(userRepository.save(oldUser), HttpStatus.OK);
        } else {
            updatedUser = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return updatedUser;
    }

    public ResponseEntity<Void> deleteUser(String id) {
        ResponseEntity<Void> response = null;
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            response = new ResponseEntity<>(HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }
}
