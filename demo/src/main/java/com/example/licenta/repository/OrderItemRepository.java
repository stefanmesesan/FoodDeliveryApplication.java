package com.example.licenta.repository;

import com.example.licenta.model.Order;
import com.example.licenta.model.OrderItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
    @Transactional
    void deleteByMenuItemId(UUID id);

    @Transactional
    void deleteAllByOrderId(UUID id);

    List<OrderItem> findAllByOrderId(UUID id);

    List<OrderItem> findAllByMenuItemId(UUID id);
}
