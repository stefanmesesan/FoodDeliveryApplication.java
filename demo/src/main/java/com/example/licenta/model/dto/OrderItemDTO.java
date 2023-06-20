package com.example.licenta.model.dto;

import java.util.Objects;
import java.util.UUID;

public class OrderItemDTO {

    private UUID id;

    private UUID orderId;

    private UUID menuItemId;

    private int quantity;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getOrder() {
        return orderId;
    }

    public void setOrder(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getMenuItem() {
        return menuItemId;
    }

    public void setMenuItemId(UUID menuItemId) {
        this.menuItemId = menuItemId;
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
        return quantity == that.quantity && Objects.equals(id, that.id) && Objects.equals(orderId, that.orderId) && Objects.equals(menuItemId, that.menuItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, menuItemId, quantity);
    }
}
