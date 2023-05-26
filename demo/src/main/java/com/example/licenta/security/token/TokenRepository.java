package com.example.licenta.security.token;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    List<Token> findAllByUserId(UUID id);

    Optional<Token> findByToken(String token);

}