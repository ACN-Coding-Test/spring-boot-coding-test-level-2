package com.accenture.codingtest.springbootcodingtest.service;

import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.model.UserDto;
import com.accenture.codingtest.springbootcodingtest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public Collection<UserDto> findAll() {
        Collection<UserDto> all = new ArrayList<>();
        userRepository.findAll().forEach(obj -> {
            all.add(modelMapper.map(obj, UserDto.class));
        });
        return all;
    }

    public UserDto findById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found. Id: " + id));
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto create(UserDto userDto) {
        User entityUser = modelMapper.map(userDto, User.class);
        User createdEntity = userRepository.save(entityUser);
        return modelMapper.map(createdEntity, UserDto.class);
    }

    public UserDto update(UUID id, UserDto userDto) {
        boolean existsById = userRepository.existsById(id);
        if (existsById) {
            User entityUser = modelMapper.map(userDto, User.class);
            User updatedEntity = userRepository.save(entityUser);
            return modelMapper.map(updatedEntity, UserDto.class);
        }
        throw new EntityNotFoundException("User not found. Id: " + id);
    }

    public UserDto updatePartially(UUID id, UserDto userDto) {
        UserDto target = findById(id);
        modelMapper.map(userDto, target);
        User targetEntity = modelMapper.map(target, User.class);
        userRepository.save(targetEntity);
        return target;
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
}
