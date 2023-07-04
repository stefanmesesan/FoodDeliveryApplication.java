package com.example.licenta.service;

import com.example.licenta.model.dto.ReviewDTO;

import java.util.List;
import java.util.UUID;

public interface ReviewService {
    ReviewDTO placeReview(ReviewDTO reviewDTO, UUID userId, UUID restaurantId);

    List<ReviewDTO> findAll(UUID id);
}
