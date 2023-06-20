package com.example.licenta.service.impl;

import com.example.licenta.exception.ApiException;
import com.example.licenta.exception.ErrorKeys;
import com.example.licenta.model.Restaurant;
import com.example.licenta.model.Review;
import com.example.licenta.model.User;
import com.example.licenta.model.dto.ReviewDTO;
import com.example.licenta.repository.RestaurantRepository;
import com.example.licenta.repository.ReviewRepository;
import com.example.licenta.repository.UserRepository;
import com.example.licenta.service.ReviewService;
import com.example.licenta.service.converter.RestaurantConverter;
import com.example.licenta.service.converter.ReviewConverter;
import com.example.licenta.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReviewServiceImpl implements ReviewService {

    final RestaurantRepository restaurantRepository;
    final ReviewRepository reviewRepository;
    final UserRepository userRepository;

    public ReviewServiceImpl(RestaurantRepository restaurantRepository, ReviewRepository reviewRepository, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    public ReviewDTO placeReview(ReviewDTO reviewDTO, UUID userId, UUID restaurantId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ApiException(Constants.USER_NOT_FOUND, ErrorKeys.NOT_FOUND, HttpStatus.NOT_FOUND));
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new ApiException("Restaurant not found!", ErrorKeys.NOT_FOUND, HttpStatus.NOT_FOUND));

        placeRating(restaurantId, reviewDTO.getRating());

        Review review = ReviewConverter.toReview(reviewDTO, user, restaurant);
        return ReviewConverter.toReviewDTO(reviewRepository.save(review));
    }

    private void placeRating(UUID restaurantId, Double rating) {
        if (rating < 1 || rating > 10)
            throw new ApiException("rating should be between 1 and 10", ErrorKeys.INVALID_RATING, HttpStatus.BAD_REQUEST);
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new ApiException("Restaurant NOT found!", ErrorKeys.NOT_FOUND, HttpStatus.NOT_FOUND));

        restaurant.setRating((restaurant.getRating() + rating) / 2);

        RestaurantConverter.toRestaurantDTO(restaurantRepository.save(restaurant));
    }
}
