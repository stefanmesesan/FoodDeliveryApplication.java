package com.example.licenta.repository;

import com.example.licenta.model.Order;
import com.example.licenta.model.OrderStatus;
import com.example.licenta.model.dto.OrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findAllByRestaurantId(UUID restaurantId);

    List<Order> findAllByOrderStatus(OrderStatus orderStatus);

    List<Order> findAllByUserId(UUID userId);
}
