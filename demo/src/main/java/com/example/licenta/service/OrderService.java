package com.example.licenta.service;

import com.example.licenta.model.dto.OrderDTO;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    List<OrderDTO> findAll();

    OrderDTO addOrder(OrderDTO orderDTO);

    void deleteOrder(UUID id);

    OrderDTO modifyOrderDetails(OrderDTO orderDTO);

    OrderDTO findById(UUID id);
}
