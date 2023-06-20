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

import static com.example.licenta.utils.Constants.USER_NOT_FOUND;

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
        return UserConverter.toUserDTO(userRepository.findUserByEmail(userEmail).orElseThrow(() -> new ApiException(USER_NOT_FOUND, ErrorKeys.NOT_FOUND, HttpStatus.NOT_FOUND)));
    }

    public UserDTO modifyUser(UUID id, UserDTO newUser) {
        User user = userRepository.findById(id).orElseThrow();

        if (newUser.getAddress() != null)
            user.setAddress(newUser.getAddress());
        if (newUser.getPhoneNumber() != null)
            user.setPhoneNumber(newUser.getPhoneNumber());
        if (newUser.getFirstName() != null)
            user.setFirstName(newUser.getFirstName());
        if (newUser.getLastName() != null)
            user.setLastName(newUser.getLastName());
        if (newUser.getPassword() != null)
            user.setPassword(newUser.getPassword());

        return UserConverter.toUserDTO(userRepository.save(user));

    }
}
