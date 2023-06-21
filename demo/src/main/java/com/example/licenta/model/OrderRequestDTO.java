package com.example.licenta.model;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class OrderRequestDTO {
    private UUID userId;
    private UUID restaurantId;
    private List<UUID> menuItemIds;
    private List<Integer> quantities;

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

    public List<UUID> getMenuItemIds() {
        return menuItemIds;
    }

    public void setMenuItemIds(List<UUID> menuItemIds) {
        this.menuItemIds = menuItemIds;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<Integer> quantities) {
        this.quantities = quantities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderRequestDTO that = (OrderRequestDTO) o;
        return Objects.equals(userId, that.userId) && Objects.equals(restaurantId, that.restaurantId) && Objects.equals(menuItemIds, that.menuItemIds) && Objects.equals(quantities, that.quantities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, restaurantId, menuItemIds, quantities);
    }
}
