package com.example.licenta.service.impl;

import com.example.licenta.repository.OrderItemRepository;
import com.example.licenta.service.OrderItemService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public void deleteByMenuItem(UUID id) {
        orderItemRepository.deleteByMenuItemId(id);
    }
}
