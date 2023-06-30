package com.example.licenta.service.converter;

import com.example.licenta.model.Order;
import com.example.licenta.model.dto.OrderDTO;

public class OrderConverter {

    public static OrderDTO toOrderDTO(Order order){
        final OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderStatus(order.getOrderStatus());
        orderDTO.setId(order.getId());
        orderDTO.setRestaurantId(order.getRestaurantId());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setOrderStatus(order.getOrderStatus());
        orderDTO.setUserId(order.getUserId());
        orderDTO.setCreatedAt(order.getCreatedAt());

        return orderDTO;
    }

    public static Order toOrder(OrderDTO orderDTO){
        final Order order = new Order();
        order.setOrderStatus(orderDTO.getOrderStatus());
        order.setId(orderDTO.getId());
        order.setRestaurantId(orderDTO.getRestaurantId());
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setOrderStatus(orderDTO.getOrderStatus());
        order.setUserId(orderDTO.getUserId());
        order.setCreatedAt(orderDTO.getCreatedAt());

        return order;
    }
}
