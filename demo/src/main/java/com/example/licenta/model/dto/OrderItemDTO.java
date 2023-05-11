package com.example.licenta.model.dto;

import com.example.licenta.model.MenuItem;
import com.example.licenta.model.Order;

import java.util.Objects;
import java.util.UUID;

public class OrderItemDTO {

    private UUID id;

    private Order order;

    private MenuItem menuItem;

    private int quantity;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItemId(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemDTO that = (OrderItemDTO) o;
        return quantity == that.quantity && Objects.equals(id, that.id) && Objects.equals(order, that.order) && Objects.equals(menuItem, that.menuItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, menuItem, quantity);
    }
}
