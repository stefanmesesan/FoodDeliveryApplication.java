package com.example.licenta.repository;

import com.example.licenta.model.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findUserByEmail(String email);
    @NotNull Optional<User> findById(@NotNull UUID id);
    boolean existsByEmail(String email);
}
