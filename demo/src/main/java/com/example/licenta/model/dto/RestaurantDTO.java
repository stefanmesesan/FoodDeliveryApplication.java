package com.example.licenta.model.dto;

import java.util.Objects;
import java.util.UUID;

public class RestaurantDTO {

    private UUID id;

    private String name;

    private String address;

    private String phoneNumber;

    private Double rating;

    private Boolean needDeletion;

    private UUID addedBy;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Boolean getNeedDeletion() {
        return needDeletion;
    }

    public void setNeedDeletion(Boolean needDeletion) {
        this.needDeletion = needDeletion;
    }

    public UUID getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(UUID addedBy) {
        this.addedBy = addedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantDTO that = (RestaurantDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(rating, that.rating) && Objects.equals(needDeletion, that.needDeletion) && Objects.equals(addedBy, that.addedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, phoneNumber, rating, needDeletion, addedBy);
    }
}
