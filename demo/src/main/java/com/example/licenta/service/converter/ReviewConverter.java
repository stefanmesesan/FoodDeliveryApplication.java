package com.example.licenta.service.converter;

import com.example.licenta.model.Restaurant;
import com.example.licenta.model.Review;
import com.example.licenta.model.User;
import com.example.licenta.model.dto.ReviewDTO;

public class ReviewConverter {

    public static ReviewDTO toReviewDTO(Review review){
        final ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setRating(review.getRating());
        reviewDTO.setComment(review.getComment());
        reviewDTO.setCreatedAt(review.getCreatedAt());

        return reviewDTO;
    }

    public static Review toReview(ReviewDTO reviewDTO, User user, Restaurant restaurant){
        final Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setRating(reviewDTO.getRating());
        review.setUser_id(user.getId());
        review.setComment(reviewDTO.getComment());
        review.setCreatedAt();
        review.setRestaurantId(restaurant.getId());

        return review;
    }
}
