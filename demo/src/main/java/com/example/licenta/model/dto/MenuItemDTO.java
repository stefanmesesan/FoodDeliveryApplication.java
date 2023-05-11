package com.example.licenta.model.dto;

import com.example.licenta.model.Restaurant;

import java.util.Objects;
import java.util.UUID;

public class MenuItemDTO {
    private UUID id;

    private String name;

    private String description;

    private Float price;

    private Restaurant restaurant;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public UUID getRestaurant_id() {
        return restaurant.getId();
    }

    public void setRestaurant_id(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItemDTO that = (MenuItemDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(price, that.price) && Objects.equals(restaurant, that.restaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, restaurant);
    }
}
