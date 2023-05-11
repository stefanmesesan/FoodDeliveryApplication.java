package com.example.licenta.service;

import com.example.licenta.model.JwtResponse;

public interface AuthService {
    JwtResponse createJwt(String accessToken);

    String refreshJwt(String refreshToken);

}