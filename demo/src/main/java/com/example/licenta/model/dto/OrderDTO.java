package com.example.licenta.model.dto;

import com.example.licenta.model.OrderStatus;
import com.example.licenta.model.Restaurant;
import com.example.licenta.model.User;

import java.util.Objects;
import java.util.UUID;

public class OrderDTO {

    private UUID id;

    private User user;

    private Restaurant restaurant;

    private OrderStatus orderStatus;

    private Double totalPrice;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUser() {
        return user.getId();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = OrderStatus.ORDER_RECEIVED;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(id, orderDTO.id) && Objects.equals(user, orderDTO.user) && Objects.equals(restaurant, orderDTO.restaurant) && orderStatus == orderDTO.orderStatus && Objects.equals(totalPrice, orderDTO.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, restaurant, orderStatus, totalPrice);
    }
}
