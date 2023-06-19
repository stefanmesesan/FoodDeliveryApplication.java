package com.example.licenta.controller;

import com.example.licenta.model.UserRole;
import com.example.licenta.security.AuthenticationRequest;
import com.example.licenta.security.AuthenticationResponse;
import com.example.licenta.security.RegisterRequest;
import com.example.licenta.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register/{userRole}")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request,
                                                           @PathVariable UserRole userRole) {
        request.setRole(userRole);
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

}
