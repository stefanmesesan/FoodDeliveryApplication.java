package com.example.licenta.service.impl;

import com.example.licenta.exception.ApiException;
import com.example.licenta.exception.ErrorKeys;
import com.example.licenta.model.dto.UserDTO;
import com.example.licenta.repository.UserRepository;
import com.example.licenta.service.UserService;
import com.example.licenta.service.converter.UserConverter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    public UserDTO findByUserEmail(String userEmail) {
        return UserConverter.toUserDTO(userRepository.findUserByEmail(userEmail).orElseThrow(() -> new ApiException("User not found!", ErrorKeys.NOT_FOUND, HttpStatus.NOT_FOUND)));
    }
}
