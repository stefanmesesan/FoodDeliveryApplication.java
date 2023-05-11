package com.example.licenta.controller;

import com.example.licenta.model.JwtResponse;
import com.example.licenta.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.licenta.utils.Constants.LOCAL_HOST_3000;
import static com.example.licenta.utils.Constants.LOGIN;
import static com.example.licenta.utils.Constants.REFRESH;

@CrossOrigin(LOCAL_HOST_3000)
@RestController
@RequestMapping(LOGIN)
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public JwtResponse createJwtToken(@RequestBody(required = false) String accessToken) {
        return authService.createJwt(accessToken);
    }

    @PostMapping(REFRESH)
    public String refreshJwt(@RequestBody(required = false) String refreshToken) {
        return authService.refreshJwt(refreshToken);
    }

}