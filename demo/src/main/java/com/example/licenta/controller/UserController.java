package com.example.licenta.controller;

import com.example.licenta.model.UserRole;
import com.example.licenta.model.dto.UserDTO;
import com.example.licenta.security.Secured;
import com.example.licenta.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/{id}")
    @Secured(role = {UserRole.ADMIN})
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUserById(id);
    }

    @PutMapping("/{id}")
    public UserDTO modifyUser(@PathVariable UUID id, @RequestBody UserDTO newUserDTO){return userService.modifyUser(id, newUserDTO);}
}
