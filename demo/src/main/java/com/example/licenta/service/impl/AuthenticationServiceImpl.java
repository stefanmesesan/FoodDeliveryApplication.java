package com.example.licenta.service.impl;

import com.example.licenta.exception.ApiException;
import com.example.licenta.exception.ErrorKeys;
import com.example.licenta.model.EmailDetails;
import com.example.licenta.model.User;
import com.example.licenta.repository.UserRepository;
import com.example.licenta.security.AuthenticationRequest;
import com.example.licenta.security.AuthenticationResponse;
import com.example.licenta.security.JwtService;
import com.example.licenta.security.RegisterRequest;
import com.example.licenta.security.token.Token;
import com.example.licenta.security.token.TokenRepository;
import com.example.licenta.security.token.TokenType;
import com.example.licenta.service.AuthenticationService;
import com.example.licenta.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(UserRepository userRepository, EmailService emailService, TokenRepository tokenRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail()))
            throw new ApiException("This user already exists!", ErrorKeys.ALREADY_EXISTS, HttpStatus.BAD_REQUEST);

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        var savedUser = userRepository.save(user);
        var access_token = jwtService.generateToken(user);

        saveUserToken(savedUser, access_token);
        sendRegistrationEmail(request);

        return AuthenticationResponse.builder()
                .access_token(access_token)
                .role(user.getRole())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow();
        var access_token = jwtService.generateToken(user);

        saveUserToken(user, access_token);

        return AuthenticationResponse.builder()
                .access_token(access_token)
                .role(user.getRole())
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void deleteAllInvalidTokens(User user) {
        var validUserTokens = tokenRepository.findAllByUserId(user.getId());
        if (validUserTokens.isEmpty())
            return;
        tokenRepository.deleteAll(validUserTokens);
    }

    private void sendRegistrationEmail(RegisterRequest request) {
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setSubject("Contul tau a fost inregistrat!");
        emailDetails.setMessage("Felicitari! Contul tau a fost creat. Acum te poti bucura de experienta oferita de aplicatia FUUD|EP! \n \n"
                + "Detaliile contului tau: \n"
                + "Email: " + request.getEmail() + "\n"
                + "Parola: " + request.getPassword() + "\n"
                + "Nume: " + request.getFirstName() + "\n"
                + "Prenume: " + request.getLastName() + "\n"
                + "Numar de telefon: " + request.getPhoneNumber() + "\n"
                + "Adresa: " + request.getAddress() + "\n"
                + "\nCu stima, \nEchipa FUUD|EP");
        emailDetails.setRecipient(request.getEmail());

        emailService.sendSimpleMail(emailDetails);
    }
}
