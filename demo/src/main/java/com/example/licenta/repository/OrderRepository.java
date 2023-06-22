package com.example.licenta.repository;

import com.example.licenta.model.Order;
import com.example.licenta.model.OrderStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    Order findByRestaurantId(UUID restaurantId);

    List<Order> findAllByOrderStatus(OrderStatus orderStatus);
    List<Order> findAllByRestaurantId(UUID restaurantId);

    List<Order> findAllByUserId(UUID userId);

    @Transactional
    void deleteAllByRestaurantId(UUID restaurantId);
}
