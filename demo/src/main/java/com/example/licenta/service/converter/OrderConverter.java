package com.example.licenta.service.converter;

import com.example.licenta.model.Order;
import com.example.licenta.model.dto.OrderDTO;

public class OrderConverter {

    public static OrderDTO toOrderDTO(Order order){
        final OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderStatus(order.getOrderStatus());
        orderDTO.setId(order.getId());
        orderDTO.setRestaurant(order.getRestaurant());
        orderDTO.setTotalPrice(order.getTotalPrice());

        return orderDTO;
    }

    public static Order toOrder(OrderDTO orderDTO){
        final Order order = new Order();
        order.setOrderStatus(orderDTO.getOrderStatus());
        order.setId(orderDTO.getId());
        order.setRestaurant(orderDTO.getRestaurant());
        order.setTotalPrice(orderDTO.getTotalPrice());

        return order;
    }
}
