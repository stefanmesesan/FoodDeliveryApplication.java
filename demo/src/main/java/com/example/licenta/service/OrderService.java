package com.example.licenta.service;

import com.example.licenta.model.dto.OrderRequestDTO;
import com.example.licenta.model.OrderStatus;
import com.example.licenta.model.UserRole;
import com.example.licenta.model.dto.OrderDTO;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    List<OrderDTO> findAll();
    List<OrderDTO> findAllByStatus(OrderStatus orderStatus);

    OrderDTO placeNewOrder(OrderRequestDTO orderRequest, UUID userId, UUID restaurantId);

    List<OrderDTO> findAllByStatusOrderReceived();
    List<OrderDTO> findAllByStatusOnItsWay();

     OrderDTO cancelOrder(UUID id, UserRole userRole);
    OrderDTO acceptOrder(UUID id, UserRole userRole);
    OrderDTO pickUpOrder(UUID id, UserRole userRole, UUID deliveryGuyId);
    OrderDTO deliverOrder(UUID id, UserRole userRole);

    OrderDTO findById(UUID id);

    void deleteOrder(UUID id);
}
