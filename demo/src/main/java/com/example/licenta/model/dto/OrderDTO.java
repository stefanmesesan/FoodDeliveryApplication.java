package com.example.licenta.model.dto;

import com.example.licenta.model.OrderStatus;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class OrderDTO {

    private UUID id;

    private UUID userId;

    private UUID restaurantId;

    private OrderStatus orderStatus;

    private Double totalPrice;

    private LocalDate createdAt;

    private UUID deliveryGuyId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getDeliveryGuyId() {
        return deliveryGuyId;
    }

    public void setDeliveryGuyId(UUID deliveryGuyId) {
        this.deliveryGuyId = deliveryGuyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(id, orderDTO.id) && Objects.equals(userId, orderDTO.userId) && Objects.equals(restaurantId, orderDTO.restaurantId) && orderStatus == orderDTO.orderStatus && Objects.equals(totalPrice, orderDTO.totalPrice) && Objects.equals(createdAt, orderDTO.createdAt) && Objects.equals(deliveryGuyId, orderDTO.deliveryGuyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, restaurantId, orderStatus, totalPrice, createdAt, deliveryGuyId);
    }
}
