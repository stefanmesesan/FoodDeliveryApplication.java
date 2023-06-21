package com.example.licenta.repository;

import com.example.licenta.model.OrderItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
    @Transactional
    void deleteByMenuItemId(UUID id);
}
