package com.example.licenta.model.dto;

import com.example.licenta.model.Restaurant;
import com.example.licenta.model.User;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class ReviewDTO {

    private UUID id;

    private Double rating;

    private String comment;

    private LocalDate createdAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
        ReviewDTO reviewDTO = (ReviewDTO) o;
        return Objects.equals(id, reviewDTO.id) && Objects.equals(rating, reviewDTO.rating) && Objects.equals(comment, reviewDTO.comment) && Objects.equals(createdAt, reviewDTO.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rating, comment, createdAt);
    }
}
