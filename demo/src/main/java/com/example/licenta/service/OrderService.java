package com.example.licenta.service;

import com.example.licenta.model.OrderStatus;
import com.example.licenta.model.UserRole;
import com.example.licenta.model.dto.OrderDTO;
import com.example.licenta.model.dto.OrderItemDTO;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    List<OrderDTO> findAll();
    List<OrderDTO> findAllByStatus(OrderStatus orderStatus);

    OrderDTO addOrder(UUID userId, UUID menuItemId);

    OrderDTO changeOrderStatus(UUID id, UserRole userRole);

    OrderDTO modifyOrderDetails(UUID id, OrderDTO orderDTO);

    OrderDTO findById(UUID id);

    void deleteOrder(UUID id);

}
