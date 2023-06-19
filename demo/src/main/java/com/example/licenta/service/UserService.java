package com.example.licenta.service;

import com.example.licenta.model.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    void deleteUserById(UUID id);
    UserDTO findByUserEmail(String userEmail);
    UserDTO modifyUser(UUID id, UserDTO userDTO);
    List<UserDTO> findAll();
}
