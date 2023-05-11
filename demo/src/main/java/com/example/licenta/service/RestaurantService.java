package com.example.licenta.service;

import com.example.licenta.model.Restaurant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestaurantService {
    List<Restaurant> findAll();
    Restaurant addRestaurant(Restaurant restaurant);
    void deleteRestaurant(UUID id);
    Restaurant modifyRestaurantDetails(Restaurant newRestaurant);

    Optional<Restaurant> findById(UUID id);

}
