package com.example.licenta.model.dto;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class OrderRequestDTO {
    private List<UUID> menuItemIds;
    private List<Integer> quantities;

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
        return Objects.equals(menuItemIds, that.menuItemIds) && Objects.equals(quantities, that.quantities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuItemIds, quantities);
    }
}
