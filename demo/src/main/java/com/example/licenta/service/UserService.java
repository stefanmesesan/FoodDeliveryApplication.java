package com.example.licenta.service;

import com.example.licenta.model.dto.UserDTO;

import java.util.UUID;

public interface UserService {
    void deleteUserById(UUID id);
    UserDTO findByUserEmail(String userEmail);
    UserDTO modifyUser(UUID id, UserDTO userDTO);
}
