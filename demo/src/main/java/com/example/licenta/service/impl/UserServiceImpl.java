package com.example.licenta.service.impl;

import com.example.licenta.exception.ApiException;
import com.example.licenta.exception.ErrorKeys;
import com.example.licenta.model.User;
import com.example.licenta.model.dto.UserDTO;
import com.example.licenta.repository.UserRepository;
import com.example.licenta.service.UserService;
import com.example.licenta.service.converter.UserConverter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserConverter::toUserDTO).toList();
    }

    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    public UserDTO findByUserEmail(String userEmail) {
        return UserConverter.toUserDTO(userRepository.findUserByEmail(userEmail).orElseThrow(() -> new ApiException("User not found!", ErrorKeys.NOT_FOUND, HttpStatus.NOT_FOUND)));
    }

    public UserDTO modifyUser(UUID id, UserDTO newUser) {
        User user = userRepository.findById(id).orElseThrow();

        user.setAddress(newUser.getAddress());
        user.setPhoneNumber(newUser.getPhoneNumber());
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setPassword(newUser.getPassword());

        return UserConverter.toUserDTO(userRepository.save(user));

    }
}
