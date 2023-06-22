package com.example.licenta.repository;

import com.example.licenta.model.MenuItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MenuItemRepository extends JpaRepository<MenuItem, UUID> {

    List<MenuItem> findAllByRestaurantId(UUID id);

    @Transactional
    void deleteAllByRestaurantId(UUID id);
}
