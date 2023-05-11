package com.example.licenta.service.impl;

import com.example.licenta.exception.ApiException;
import com.example.licenta.exception.ErrorKeys;
import com.example.licenta.model.JwtResponse;
import com.example.licenta.model.User;
import com.example.licenta.repository.UserRepository;
import com.example.licenta.service.AuthService;
import com.example.licenta.utils.JwtUtils;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Date;

import static com.example.licenta.utils.Constants.*;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final GoogleIdTokenVerifier verifier;

    public AuthServiceImpl(UserRepository userRepository, GoogleIdTokenVerifier verifier) {
        this.userRepository = userRepository;

        if (verifier == null)
            this.verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                    .setAudience(Collections.singletonList(CLIENT_ID))
                    .build();
        else
            this.verifier = verifier;
    }

    @Override
    public JwtResponse createJwt(String accessToken) {
        try {
            User user;

            GoogleIdToken idToken = GoogleIdToken.parse(verifier.getJsonFactory(), accessToken);

            if (!verifier.verify(idToken))
                throw new ApiException("The token is invalid", ErrorKeys.INVALID_TOKEN, HttpStatus.BAD_REQUEST);

            GoogleIdToken.Payload payload = idToken.getPayload();
            user = userRepository.findUserByEmail(payload.getEmail()).orElseThrow(() -> new ApiException("User not found!", ErrorKeys.USER_NOT_FOUND, HttpStatus.NOT_FOUND));

            String jwt = generateJWT(user);
            String refreshToken = generateRefreshToken(user);

            return new JwtResponse(jwt, refreshToken);

        } catch (IOException | GeneralSecurityException exception) {
            throw new ApiException("Error while creating JWT", ErrorKeys.INVALID_TOKEN_ID, HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public String refreshJwt(String refreshToken) {
        JwtUtils jwtUtils = new JwtUtils();
        jwtUtils.validateToken(refreshToken, true);

        String email = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(refreshToken)
                .getBody()
                .get(USER_DETAILS_EMAIL, String.class);

        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new ApiException("User not found!", ErrorKeys.USER_NOT_FOUND, HttpStatus.NOT_FOUND));

        return generateJWT(user);
    }

    private String generateJWT(User user) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .claim(USER_DETAILS_EMAIL, user.getEmail())
                .claim(AUTHORITIES, user.getRole())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + JWT_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    private String generateRefreshToken(User user) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .claim(USER_DETAILS_EMAIL, user.getEmail())
                .claim(AUTHORITIES, user.getRole())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
}
