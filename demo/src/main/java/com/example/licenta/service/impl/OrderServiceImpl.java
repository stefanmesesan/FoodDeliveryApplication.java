package com.example.licenta.service.impl;

import com.example.licenta.exception.ApiException;
import com.example.licenta.model.Order;
import com.example.licenta.model.OrderStatus;
import com.example.licenta.model.UserRole;
import com.example.licenta.model.dto.OrderDTO;
import com.example.licenta.repository.OrderRepository;
import com.example.licenta.service.OrderService;
import com.example.licenta.service.converter.OrderConverter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.licenta.exception.ErrorKeys.INVALID_STATUS;
import static com.example.licenta.exception.ErrorKeys.UNAUTHORIZED;
import static com.example.licenta.exception.ErrorKeys.NOT_FOUND;
import static com.example.licenta.model.OrderStatus.NEW;
import static com.example.licenta.model.OrderStatus.ON_ITS_WAY;
import static com.example.licenta.model.OrderStatus.ORDER_RECEIVED;
import static com.example.licenta.model.OrderStatus.ORDER_CANCELED;
import static com.example.licenta.utils.Constants.NOT_ENOUGH_AUTHORITIES;


@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderDTO> findAll() {
        return orderRepository.findAll().stream().map(OrderConverter::toOrderDTO).toList();
    }

    public List<OrderDTO> findAllByStatus(OrderStatus orderStatus) {
        return orderRepository.findAllByOrderStatus(OrderStatus.ORDER_CANCELED).stream().map(OrderConverter::toOrderDTO).toList();

    }

    public OrderDTO findById(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ApiException("Restaurant not found", NOT_FOUND, HttpStatus.NOT_FOUND));
        return OrderConverter.toOrderDTO(order);
    }

    public OrderDTO addOrder(OrderDTO orderDTO) {
        Order order = OrderConverter.toOrder(orderDTO);
        order.setOrderStatus(NEW);
        return OrderConverter.toOrderDTO(orderRepository.save(order));
    }

    public OrderDTO changeOrderStatus(UUID id, UserRole userRole) {
        Order order = orderRepository.findById(id).orElseThrow();
        if (userRole == UserRole.CUSTOMER)
            order.setOrderStatus(ORDER_CANCELED);
        if (order.getOrderStatus() != ORDER_CANCELED)
            if (userRole == UserRole.DELIVERY_GUY) {
                order.setOrderStatus(ON_ITS_WAY);
                return OrderConverter.toOrderDTO(orderRepository.save(order));
            } else if (userRole == UserRole.RESTAURANT_OPERATOR) {
                order.setOrderStatus(ORDER_RECEIVED);
            }
        throw new ApiException(NOT_ENOUGH_AUTHORITIES, UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
    }

    public OrderDTO modifyOrderDetails(UUID id, OrderDTO newOrder) {
        Order order = orderRepository.findById(id).orElseThrow();

        order.setRestaurant(newOrder.getRestaurant());
        order.setOrderStatus(newOrder.getOrderStatus());
        order.setTotalPrice(newOrder.getTotalPrice());

        return OrderConverter.toOrderDTO(orderRepository.save(order));
    }

    public void deleteOrder(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow();
        if (!order.getOrderStatus().equals(OrderStatus.ORDER_CANCELED))
            throw new ApiException("Invalid Order Status", INVALID_STATUS, HttpStatus.BAD_REQUEST);

        orderRepository.deleteById(id);
    }
}
