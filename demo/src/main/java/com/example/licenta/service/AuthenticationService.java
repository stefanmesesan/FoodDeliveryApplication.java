package com.example.licenta.service;

import com.example.licenta.security.AuthenticationRequest;
import com.example.licenta.security.AuthenticationResponse;
import com.example.licenta.security.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
