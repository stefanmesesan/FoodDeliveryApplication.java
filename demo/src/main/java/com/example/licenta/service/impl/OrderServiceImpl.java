package com.example.licenta.service.impl;

import com.example.licenta.exception.ApiException;
import com.example.licenta.model.Order;
import com.example.licenta.model.dto.OrderDTO;
import com.example.licenta.repository.OrderRepository;
import com.example.licenta.service.OrderService;
import com.example.licenta.service.converter.OrderConverter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.licenta.exception.ErrorKeys.NOT_FOUND;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderDTO> findAll() {
        return orderRepository.findAll().stream().map(OrderConverter::toOrderDTO).toList();
    }

    public OrderDTO findById(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ApiException("Restaurant not found", NOT_FOUND, HttpStatus.NOT_FOUND));
        return OrderConverter.toOrderDTO(order);
    }

    public OrderDTO addOrder(OrderDTO orderDTO) {
        Order order = OrderConverter.toOrder(orderDTO);
        return OrderConverter.toOrderDTO(orderRepository.save(order));
    }

    public void deleteOrder(UUID id) {
        orderRepository.deleteById(id);
    }

    public OrderDTO modifyOrderDetails(OrderDTO newOrder) {
        Order order = orderRepository.findById(newOrder.getId()).orElseThrow();

        order.setRestaurant(newOrder.getRestaurant());
        order.setOrderStatus(newOrder.getOrderStatus());
        order.setTotalPrice(newOrder.getTotalPrice());

        return OrderConverter.toOrderDTO(orderRepository.save(order));
    }
}
