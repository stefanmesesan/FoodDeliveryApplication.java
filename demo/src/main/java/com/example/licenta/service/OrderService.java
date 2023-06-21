package com.example.licenta.service;

import com.example.licenta.model.OrderRequestDTO;
import com.example.licenta.model.OrderStatus;
import com.example.licenta.model.UserRole;
import com.example.licenta.model.dto.OrderDTO;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    List<OrderDTO> findAll();
    List<OrderDTO> findAllByStatus(OrderStatus orderStatus);

    OrderDTO placeNewOrder(OrderRequestDTO orderRequest, UUID userId, UUID restaurantId);

    OrderDTO changeOrderStatus(UUID id, UserRole userRole);

    OrderDTO findById(UUID id);

    void deleteOrder(UUID id);

}
