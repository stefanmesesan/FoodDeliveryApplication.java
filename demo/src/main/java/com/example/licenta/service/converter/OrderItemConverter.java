package com.example.licenta.service.converter;

import com.example.licenta.model.OrderItem;
import com.example.licenta.model.dto.OrderItemDTO;

public class OrderItemConverter {

    public static OrderItemDTO toOrderItemDTO(OrderItem orderItem){
        final OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setOrder(orderItem.getOrder());
        orderItemDTO.setId(orderItem.getId());
        orderItemDTO.setMenuItemId(orderItem.getMenuItemId());
        orderItemDTO.setQuantity(orderItem.getQuantity());

        return orderItemDTO;
    }

    public static OrderItem toOrderItem(OrderItemDTO orderItemDTO){
        final OrderItem orderItem = new OrderItem();
        orderItem.setOrder(orderItemDTO.getOrder());
        orderItem.setId(orderItemDTO.getId());
        orderItem.setMenuItemId(orderItemDTO.getMenuItem());
        orderItem.setQuantity(orderItemDTO.getQuantity());

        return orderItem;
    }

}
