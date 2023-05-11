package com.example.licenta.model.dto;

import com.example.licenta.model.User;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class ReviewsDTO {

    private UUID id;

    private User user;

    private UUID restaurantId;

    private Double rating;

    private String comment;

    private LocalDate createdAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getCreatedAt() {
        return LocalDate.now();
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = LocalDate.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewsDTO that = (ReviewsDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(restaurantId, that.restaurantId) && Objects.equals(rating, that.rating) && Objects.equals(comment, that.comment) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, restaurantId, rating, comment, createdAt);
    }
}
